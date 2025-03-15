package com.buge.crypto.home.features.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buge.crypto.home.datasource.WalletDataSourceCentral
import com.buge.crypto.home.datasource.model.BalanceInfo
import com.buge.crypto.home.datasource.model.SymbolInfo
import com.buge.crypto.home.datasource.model.TierInfo
import com.buge.crypto.home.features.model.AssetBalanceData
import com.buge.crypto.home.utils.CalculateUtil
import com.buge.crypto.home.utils.NumberFormatUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletBalanceViewModel : ViewModel() {

    private val _combineBalances = MediatorLiveData<List<AssetBalanceData>>()
    val combineBalances = _combineBalances

    private val _totalBalance = MediatorLiveData<String>()
    val totalBalance = _totalBalance

    /**
     * 考虑到这三个数据，未来不一定是从文件、接口中获取，
     * 还有可能是ws数据下发的情况，所以需要对这些数据进行存储，
     * 未来如果部分数据ws数据下发的时候，可以实时监听并更新
     * */
    private val _symbolMap = MutableLiveData<Map<String, SymbolInfo>>()
    private val _rateMap = MutableLiveData<Map<String, TierInfo>>()
    private val _balanceList = MutableLiveData<List<BalanceInfo>>()

    init {
        _combineBalances.addSource(_symbolMap) { combineBalanceData() }
        _combineBalances.addSource(_rateMap) { combineBalanceData() }
        _combineBalances.addSource(_balanceList) { combineBalanceData() }

        _totalBalance.addSource(_combineBalances) {}
    }

    /**
     * init loading all datas
     * */
    fun initData() {

        viewModelScope.launch {
            /**
             * Loading start
             * */
            combine(
                WalletDataSourceCentral.walletDataSource.getSymbolList(),
                WalletDataSourceCentral.walletDataSource.getRatesList(),
                WalletDataSourceCentral.walletDataSource.getWalletBalanceList()
            ) { symbolsResponse, ratesResponse, balancesResponse ->
                val symbolMap =
                    symbolsResponse.currencies?.filter { !it.coinId.isNullOrEmpty() }?.associate {
                        (it.coinId ?: "") to it
                    }
                symbolMap?.let {
                    _symbolMap.postValue(it)
                }

                val rateMap = ratesResponse.tiers?.filter {
                    !it.fromCurrency.isNullOrEmpty() && !it.toCurrency.isNullOrEmpty()
                }?.associate {
                    ("${it.fromCurrency}${it.toCurrency}" to it)
                }
                rateMap?.let {
                    _rateMap.postValue(it)
                }
                _balanceList.postValue(balancesResponse.wallet)
            }.flowOn(Dispatchers.IO).collect {
                /**
                 * Loading end
                 * */
            }
        }

    }

    private fun combineBalanceData() {
        viewModelScope.launch {
            val combinedList = withContext(Dispatchers.Default) {
                val symbols = _symbolMap.value ?: return@withContext null
                val rates = _rateMap.value ?: return@withContext null
                val balances = _balanceList.value ?: return@withContext null
                CalculateUtil.calculateBalanceList(
                    symbols, rates, balances
                )
            }
            combinedList?.let {
                _totalBalance.value =
                    "${CalculateUtil.DEFAULT_CURRENCY_SYMBOL} ${NumberFormatUtil.format(it.first)} ${CalculateUtil.DEFAULT_CURRENCY}"
                _combineBalances.value = it.second
            }
        }
    }

}