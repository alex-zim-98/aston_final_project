package com.example.aston_final_project.di

import com.example.aston_final_project.presentation.view.model.DefaultToolbarModel
import dagger.Module
import dagger.Provides

@Module
class CustomToolbarModule {
    @Provides
    fun provideCustomToolbarModule(@ToolbarTitle title: String): DefaultToolbarModel {
        return DefaultToolbarModel(title)
    }
}