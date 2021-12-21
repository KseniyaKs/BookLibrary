package com.example.myapplicationone.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationone.APIService
import com.example.myapplicationone.dataClass.ListBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel: ViewModel() {

    var data: MutableLiveData<ListBook> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "onCleared")
    }

    init {
        Log.d("TAG", "create MyViewModel")
        loadData(searchTxt = "book")
    }

    fun loadData(searchTxt : String?){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: APIService = retrofit.create(APIService::class.java)

        service.getTop("10", searchTxt)
            .enqueue(object : Callback<ListBook> {
                override fun onResponse(call: Call<ListBook>?, response: Response<ListBook>?) {

                    response?.body()?.let {
                        data?.value = it
                        Log.d("TAG", "response.body()")
//                        Log.d("TAG", "тут mainrecreate")
                    }
                }

                override fun onFailure(call: Call<ListBook>?, t: Throwable?) {
                    Log.d("hjk", "hjkl")
                }
            })
    }
}