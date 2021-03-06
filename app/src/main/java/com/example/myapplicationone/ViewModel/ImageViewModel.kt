package com.example.myapplicationone.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationone.APIService
import com.example.myapplicationone.dataclass.Book
import com.example.myapplicationone.dataclass.BookDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImageViewModel: ViewModel() {

    var data: MutableLiveData<BookDetails> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "onCleared")
    }

    init {
        Log.d("TAG", "create ImageViewModel")
    }

    fun loadData(book: Book){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: APIService = retrofit.create(APIService::class.java)

        service.getBook(book.id)
            .enqueue(object : Callback<BookDetails> {
                override fun onResponse(call: Call<BookDetails>?, response: Response<BookDetails>?) {
                    response?.body()?.let {
                        data.value = it

                        Log.d("TAG", "response.body()")
                        Log.d("TAG","callback in ImageViewModel")
                    }
                }

                override fun onFailure(call: Call<BookDetails>?, t: Throwable?) {
                    Log.d("hjk", "hjkl")
                }
            })
    }
}