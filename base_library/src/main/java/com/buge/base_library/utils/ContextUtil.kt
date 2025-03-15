package com.buge.base_library.utils

import android.app.Application
import com.buge.base_library.interfaces.ApplicationProvider

object ContextUtil {
    private lateinit var applicationProvider: ApplicationProvider

    fun init(provider: ApplicationProvider) {
        applicationProvider = provider
    }

    fun getApplication(): Application? {
        if (!::applicationProvider.isInitialized) {
            return null
        }
        return applicationProvider.getApplication()
    }
}