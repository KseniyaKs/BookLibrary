package com.example.myapplicationone.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationone.request.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            MainViewModel::class.java -> MainViewModel(repository) as T
            SecondViewModel::class.java -> SecondViewModel(repository) as T
            else -> throw IllegalArgumentException()
        }

//        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
//            MainViewModel as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
    }
}