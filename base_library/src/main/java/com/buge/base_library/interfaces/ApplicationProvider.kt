package com.buge.base_library.interfaces

import android.app.Application

interface ApplicationProvider {
    fun getApplication(): Application
}