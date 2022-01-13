package com.example.myapplicationone.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationone.APIService
import com.example.myapplicationone.dataclass.Book
import com.example.myapplicationone.dataclass.BookDetails
import com.example.myapplicationone.request.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SecondViewModel (val repository: Repository) : ViewModel() {
    var data: MutableLiveData<BookDetails> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "onCleared")
    }

    init {
        Log.d("TAG", "create SecondViewModel")
    }

    fun loadData(book: Book){
        repository.getBookById(data,book)
    }
}