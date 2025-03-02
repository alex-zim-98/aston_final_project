package com.example.aston_final_project.di

import android.content.Context
import com.example.aston_final_project.fragments.HeadlinesFragment
import com.example.aston_final_project.activity.MainActivity
import com.example.aston_final_project.fragments.SavedFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HeadlinesFragment)
    fun inject(fragment: SavedFragment)
    fun customToolbarComponentFactory(): CustomToolbarComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}