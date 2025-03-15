package com.buge.crypto.home.datasource

import com.buge.crypto.home.datasource.model.SymbolListResponse
import kotlinx.coroutines.flow.Flow

/**
 * 定义数据获取接口
 * */
interface WalletDataSource {

    suspend fun getSymbolList(): Flow<SymbolListResponse>

}