package com.example.aston_final_project.app

import android.app.Application
import com.example.aston_final_project.di.AppComponent
import com.example.aston_final_project.di.DaggerAppComponent

class MyApp: Application(), App {
    private val component by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun getAppComponent(): AppComponent {
        return component
    }


}