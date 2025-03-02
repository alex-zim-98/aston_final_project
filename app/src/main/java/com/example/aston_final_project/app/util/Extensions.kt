package com.example.aston_final_project.app.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import com.example.aston_final_project.app.network.NetworkManager

fun <T: View> View.find(@IdRes idRes: Int): Lazy<T> {
    return lazy {
        val view = findViewById<T>(idRes)
        if (view == null) {
            Log.e("find", "View with ID $idRes not found")
        }
        checkNotNull(view) { "View with ID $idRes not found" }
    }
}

fun NetworkManager.checkInternetConnection(doOnSuccess: () -> Unit, doOnFail: () -> Unit) {
    if (this.isInternetAvailable()) doOnSuccess.invoke()
    else doOnFail.invoke()
}

fun String.showText(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}


