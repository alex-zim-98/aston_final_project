package com.example.aston_final_project.presentation.helper

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ImageHelper @Inject constructor() {
    fun resToDrawable(context: Context, res: Int): Drawable? {
        return ContextCompat.getDrawable(context, res)
    }
}