package com.buge.crypto.home.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buge.crypto.home.datasource.WalletDataSouceCentral
import com.buge.crypto.home.datasource.model.SymbolInfo
import com.buge.crypto.home.features.model.AssetBalanceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WalletBalanceViewModel : ViewModel() {

    private val _balanceList = MutableLiveData<List<AssetBalanceData>>()
    val balanceList = _balanceList

    private val _symbolList = MutableLiveData<List<SymbolInfo>>()

    /**
     * Loading symbol list data
     * */
    fun loadSymbolListData() {
        viewModelScope.launch {
            WalletDataSouceCentral.walletDataSource
                .getSymbolList()
                .flowOn(Dispatchers.IO)
                .collect {
                    _symbolList.value = it.currencies
                }
        }
    }

}