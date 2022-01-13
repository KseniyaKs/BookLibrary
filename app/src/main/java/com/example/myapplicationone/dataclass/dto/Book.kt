package com.example.myapplicationone.dataclass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.myapplicationone.dataclass.dto.Images
import com.squareup.moshi.Json
import java.io.Serializable
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


data class ListBook(@field:Json(name = "items") val list: List<Book>)

data class Book(
    @field:Json(name = "volumeInfo") val volumeInfo: VolumeInfo,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "isLike") var isLike: Boolean = false) : Serializable



data class BookDetails(
    @field:Json(name = "volumeInfo") val volumeInfo: VolumeInfo,
    @field:Json(name = "saleInfo") val saleInfo: SaleInfo
)

data class VolumeInfo(
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "authors") val author: List<String>?,
    @field:Json(name = "imageLinks") val imgLinks: Images?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "publishedDate") val date: String?,
    @field:Json(name = "previewLink") val previewLink: String?
)

data class SaleInfo(@field:Json(name = "listPrice") val listPrice: ListPrice?)

data class ListPrice(
    @field:Json(name = "amount") val amount: Double,
    @field:Json(name = "currencyCode") val currencyCode: String
)


class authorsConverter {
    @TypeConverter
    fun toAuthors(value: String?): List<String>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toListAuthors(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}

class imagesConverter {
    @TypeConverter
    fun toString(image: Images): String {
        val gson = Gson()
        return gson.toJson(image)
    }

    fun toImage(value: String): Images {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}

