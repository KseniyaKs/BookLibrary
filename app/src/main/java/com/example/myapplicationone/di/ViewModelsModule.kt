package com.example.myapplicationone.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationone.ViewModel.MainViewModel
import com.example.myapplicationone.ViewModel.ViewModelFactory
import com.example.myapplicationone.request.Repository
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {
    @Provides
    fun provideViewModelFactory(repository: Repository): ViewModelFactory{
        return ViewModelFactory(repository)
//         val model: MainViewModel by viewModels { ViewModelFactory }
//        ViewModelProvider.of(this, ViewModelFactory(repository)).get(ViewModel::class.java)
    }
}