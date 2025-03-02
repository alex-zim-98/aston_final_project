package com.example.aston_final_project.di

import android.content.Context
import com.example.aston_final_project.presentation.fragment.HeadlinesFragment
import com.example.aston_final_project.presentation.activity.MainActivity
import com.example.aston_final_project.presentation.fragment.SavedFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ViewModelFactoryModule::class,
    RepositoryModule::class])
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