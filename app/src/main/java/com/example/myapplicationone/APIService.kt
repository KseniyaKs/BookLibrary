package com.example.myapplicationone


import com.example.myapplicationone.dataclass.BookDetails
import com.example.myapplicationone.dataclass.ListBook
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("/books/v1/volumes/")
    fun getTop(
        @Query("maxResults") maxResults: String,
        @Query("q") q: String?
    ): Call<ListBook>

    @GET("/books/v1/volumes/{id}")
    fun getBook(@Path("id") id: String?): Call<BookDetails>


}