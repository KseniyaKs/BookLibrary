package com.example.myapplicationone.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.myapplicationone.App
import com.example.myapplicationone.fragment.LikeFragment
import com.example.myapplicationone.fragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class, RetrofitModule::class, ViewModelsModule::class])
interface AppComponent {
    fun inject(fragment: LikeFragment)

    fun inject(fragment: MainFragment)

    fun inject(app: App)

}