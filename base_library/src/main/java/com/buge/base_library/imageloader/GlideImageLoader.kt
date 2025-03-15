package com.buge.base_library.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * 简单实现Glide 的加载接口，功能满足目前调用
 * 还有其它功能未来需要实现：
 *  1. 更多图片加载的接口
 *  2. Glide初始化配置
 * */
class GlideImageLoader : ImageLoader {
    override fun load(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}