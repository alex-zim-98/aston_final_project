package com.example.aston_final_project

import android.app.Application
import com.example.aston_final_project.di.AppComponent
import com.example.aston_final_project.di.DaggerAppComponent

class MyApp: Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(applicationContext)
    }
}