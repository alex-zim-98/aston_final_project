package com.example.aston_final_project.app.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.aston_final_project.R

fun ImageView.validateImage(imageUrl: String) {
    val notFoundIcon = R.drawable.noimage
    if (imageUrl.isBlank() || !imageUrl.startsWith(FILTER_URL)) {
        this.setImageResource(notFoundIcon)
    } else {
        Glide.with(this.context)
            .load(imageUrl)
            .error(notFoundIcon)
            .into(this)
    }
}

private const val FILTER_URL = "https://"