package com.buge.crypto.home.datasource

import com.buge.crypto.home.datasource.impl.WalletDataSourceImpl

object WalletDataSourceCentral {

    val walletDataSource: WalletDataSource by lazy {
        WalletDataSourceImpl()
    }
}