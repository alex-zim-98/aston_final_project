package com.example.aston_final_project.di

import android.content.Context
import com.example.aston_final_project.views.DefaultToolbarModel
import com.example.aston_final_project.R
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CustomToolbarModule {
    @Provides
    fun provideCustomToolbarModule(@ToolbarTitle title: String): DefaultToolbarModel {
        return DefaultToolbarModel(title)
    }
}