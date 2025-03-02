package com.example.aston_final_project.di

import com.example.aston_final_project.presentation.view.toolbar.CustomToolbar
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CustomToolbarModule::class])
interface CustomToolbarComponent {
    fun inject(customToolbar: CustomToolbar)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @ToolbarTitle title: String): CustomToolbarComponent
    }
}