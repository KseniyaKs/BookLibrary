package com.example.myapplicationone

import android.app.Application
import android.content.Context
import com.example.myapplicationone.dataclass.AppDatabase

import androidx.room.Room
import com.example.myapplicationone.di.AppComponent
import com.example.myapplicationone.di.AppModule
import com.example.myapplicationone.di.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }

