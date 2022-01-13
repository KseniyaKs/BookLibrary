package com.example.myapplicationone.request

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplicationone.APIService
import com.example.myapplicationone.dataclass.Book
import com.example.myapplicationone.dataclass.BookDetails
import com.example.myapplicationone.dataclass.ListBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(val service: APIService) {

    fun callSearchBookList(liveData: MutableLiveData<ListBook>, searchTxt : String?) {

        service.getTop("25", searchTxt)
            .enqueue(object : Callback<ListBook> {
                override fun onResponse(call: Call<ListBook>?, response: Response<ListBook>?) {
                    response?.body()?.let {
                        liveData.value = it
                        Log.d("TAG", "response.body()")
                    }
                }

                override fun onFailure(call: Call<ListBook>?, t: Throwable?) {
                    Log.d("hjk", "hjkl")
                }
            })
    }

    fun getBookById(liveData: MutableLiveData<BookDetails>,book: Book){
        service.getBook(book.id)
            .enqueue(object : Callback<BookDetails> {
                override fun onResponse(call: Call<BookDetails>?, response: Response<BookDetails>?) {
                    response?.body()?.let {
                        liveData.value = it

                        Log.d("TAG", "callback in SecViewModel")
                    }
                }

                override fun onFailure(call: Call<BookDetails>?, t: Throwable?) {
                    Log.d("hjk", "hjkl")
                }
            })
    }
}