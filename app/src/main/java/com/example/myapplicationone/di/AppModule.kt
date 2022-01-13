package com.example.myapplicationone.di

import android.content.Context
import dagger.Module
import dagger.Provides

import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

//    @Singleton
//    @Provides
//    fun provideRetrofitInstance() : Retrofit{
//        return retrofit
//    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }
}