package com.buge.base_library.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, url: String)
}