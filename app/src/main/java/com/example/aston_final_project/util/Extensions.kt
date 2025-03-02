package com.example.aston_final_project.util

import android.util.Log
import android.view.View
import androidx.annotation.IdRes

fun <T: View> View.find(@IdRes idRes: Int): Lazy<T> {
    return lazy {
        val view = findViewById<T>(idRes)
        if (view == null) {
            Log.e("find", "View with ID $idRes not found")
        }
        checkNotNull(view) { "View with ID $idRes not found" }
    }
}


