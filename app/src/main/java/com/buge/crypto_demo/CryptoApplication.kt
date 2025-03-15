package com.buge.crypto_demo

import android.app.Application
import com.buge.base_library.interfaces.ApplicationProvider
import com.buge.base_library.utils.ContextUtil

class CryptoApplication : Application(), ApplicationProvider {

    override fun onCreate() {
        super.onCreate()
        ContextUtil.init(this)
    }

    override fun getApplication(): Application {
        return this
    }
}