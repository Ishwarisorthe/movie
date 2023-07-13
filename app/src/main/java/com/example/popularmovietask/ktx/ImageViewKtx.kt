package com.example.popularmovietask.ktx

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.popularmovietask.R

fun ImageView.loadImageWithGlide(imageUrl: String, imagePlaceholder: Int = R.drawable.pg_images) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(imagePlaceholder)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .dontAnimate()
        .into(this)
}
