package com.buge.crypto.home.datasource

import com.buge.crypto.home.datasource.impl.WalletDataSourceImpl

object WalletDataSouceCentral {

    val walletDataSource: WalletDataSource by lazy {
        WalletDataSourceImpl()
    }
}