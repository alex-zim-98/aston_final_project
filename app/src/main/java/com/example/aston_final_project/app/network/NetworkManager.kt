package com.example.aston_final_project.app.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkManager @Inject constructor(private val context: Context) {
    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork != null
    }
}