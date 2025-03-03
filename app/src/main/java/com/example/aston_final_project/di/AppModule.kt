package com.example.aston_final_project.di

import android.content.Context
import com.example.aston_final_project.data.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
interface AppModule {

    companion object {
        @Provides
        fun provideAppDatabase(context: Context): AppDatabase {
            return AppDatabase.getDataBase(context)
        }
    }
}