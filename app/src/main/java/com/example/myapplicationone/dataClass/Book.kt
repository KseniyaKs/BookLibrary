package com.example.myapplicationone.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

data class ListBook(@field:Json(name = "items") val list: List<Book>)

@Entity
data class Book(@field:Json(name = "volumeInfo" ) val volumeInfo: VolumeInfo,
                @PrimaryKey
                @field:Json(name = "id") val id: String) : Serializable

data class BookDetails(@field:Json(name = "volumeInfo" ) val volumeInfo: VolumeInfo,
                       @field:Json(name = "saleInfo") val saleInfo: SaleInfo
)

data class VolumeInfo(@field:Json(name = "title") val title: String?,
                      @field:Json(name = "authors") val author: List<String>?,
                      @field:Json(name = "imageLinks") val imgLinks: Images?,
                      @field:Json(name = "description") val description: String?,
                      @field:Json(name = "publishedDate") val date: String?,
                      @field:Json(name = "previewLink") val previewLink: String?)

data class SaleInfo(@field:Json(name = "listPrice") val listPrice: ListPrice?)

data class ListPrice (@field:Json(name = "amount") val amount: Double,
                      @field:Json(name = "currencyCode") val currencyCode: String)

//data class UserEntity(@field:Json(name = "id") val id: String,
//                      @field:Json(name = "name") val name: String)

