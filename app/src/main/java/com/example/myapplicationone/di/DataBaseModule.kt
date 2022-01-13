package com.example.myapplicationone.di

import android.content.Context
import androidx.room.Room
import com.example.myapplicationone.dataclass.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provide(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}