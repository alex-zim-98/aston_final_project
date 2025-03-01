package com.example.aston_final_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    val testLiveData: MutableLiveData<String> = MutableLiveData()
    fun sendTextQuery(text: String) {
        testLiveData.value = text
        Log.d("sendTextQuery", text)
    }
}