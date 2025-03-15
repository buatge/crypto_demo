package com.buge.base_library.imageloader

import android.widget.ImageView

object ImageLoaderManager {
    private var imageLoader: ImageLoader = GlideImageLoader()

    fun setImageLoader(loader: ImageLoader) {
        imageLoader = loader
    }

    fun load(imageView: ImageView, url: String) {
        imageLoader.load(imageView, url)
    }
}

fun ImageView.loadImage(url: String) {
    ImageLoaderManager.load(this, url)
}