package com.buge.crypto.home.datasource.impl

import com.buge.base_library.utils.ContextUtil
import com.buge.crypto.home.datasource.WalletDataSource
import com.buge.crypto.home.datasource.model.RatesListResponse
import com.buge.crypto.home.datasource.model.SymbolListResponse
import com.buge.crypto.home.datasource.model.WalletBalanceResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import java.io.IOException

/**
 * 数据获取接口实现：
 *  使用文件读取方式获取数据
 * */
class WalletDataSourceImpl : WalletDataSource {

    private val symbolListFilePath = "currencies.json"
    private val rateListFilePath = "live-rates.json"
    private val balanceListFilePath = "wallet-balance.json"

    override suspend fun getSymbolList() = flow {
        try {
            val assetManager = ContextUtil.getApplication()?.assets
            val jsonContent = assetManager?.open(symbolListFilePath)
                ?.bufferedReader().use {
                    it?.readText()
                }
            val gson = Gson()
            val currencyResponse = gson.fromJson(jsonContent, SymbolListResponse::class.java)
            emit(currencyResponse)
        } catch (e: IOException) {
            // 读取文件出错时发射错误信息
            emit(SymbolListResponse(emptyList(), 0, false))
        }
    }

    override suspend fun getRatesList() = flow {
        try {
            val assetManager = ContextUtil.getApplication()?.assets
            val jsonContent = assetManager?.open(rateListFilePath)
                ?.bufferedReader().use {
                    it?.readText()
                }
            val gson = Gson()
            val currencyResponse = gson.fromJson(jsonContent, RatesListResponse::class.java)
            emit(currencyResponse)
        } catch (e: IOException) {
            // 读取文件出错时发射错误信息
            emit(RatesListResponse(emptyList(), "", false))
        }
    }

    override suspend fun getWalletBalanceList() = flow {
        try {
            val assetManager = ContextUtil.getApplication()?.assets
            val jsonContent = assetManager?.open(balanceListFilePath)
                ?.bufferedReader().use {
                    it?.readText()
                }
            val gson = Gson()
            val currencyResponse = gson.fromJson(jsonContent, WalletBalanceResponse::class.java)
            emit(currencyResponse)
        } catch (e: IOException) {
            // 读取文件出错时发射错误信息
            emit(WalletBalanceResponse(emptyList(), "", false))
        }
    }
}