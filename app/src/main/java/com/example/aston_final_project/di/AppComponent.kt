package com.example.aston_final_project.di

import android.content.Context
import com.example.aston_final_project.MainActivity
import com.example.aston_final_project.views.SearchToolbar
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(searchToolbar: SearchToolbar)
    fun customToolbarComponentFactory(): CustomToolbarComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}