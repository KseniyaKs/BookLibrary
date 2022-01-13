package com.example.myapplicationone.dataclass.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationone.dataclass.VolumeInfo
import java.io.Serializable

@Entity
data class BookEntity(
    @Embedded
    val volumeInfo: VolumeInfoEntity,
    @PrimaryKey
    val id: String,
    var isLike: Boolean = false) : Serializable

data class VolumeInfoEntity(
    val title: String?,
    val author: List<String>?,
    @Embedded val imgLinks: ImagesEntity?,
    val description: String?,
    val date: String?,
    val previewLink: String?
)

data class ImagesEntity (val smallImg: String?) : Serializable

