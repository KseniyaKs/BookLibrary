package com.example.myapplicationone

import android.app.Application
import com.example.myapplicationone.dataClass.AppDatabase

import androidx.room.Room




class App: Application() {
    private var dataBase : AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this,AppDatabase::class.java,"database").build()
    }

    fun getInstance(): App {
        return this
    }

    fun getDatabase(): AppDatabase? {
        return dataBase
    }
}



//class App : Application() {
//    var database: AppDatabase? = null
//        private set
//
//    override fun onCreate() {
//        super.onCreate()
//        App.Companion.instance = this
//        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
//            .build()
//    }
//
//    companion object {
//        var instance: App? = null
//        fun getInstance(): App {
//            return App.Companion.instance
//        }
//    }
//}
