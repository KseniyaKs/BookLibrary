package com.example.myapplicationone.dataclass

import com.example.myapplicationone.dataclass.dto.Images
import com.example.myapplicationone.dataclass.entity.BookEntity
import com.example.myapplicationone.dataclass.entity.ImagesEntity
import com.example.myapplicationone.dataclass.entity.VolumeInfoEntity

object BookMapper {
    fun mapFromBookToBookEntity(book: Book): BookEntity {
        return BookEntity(
            mapFromVolumeInfoToVolumeInfoEntity(book.volumeInfo), book.id,
            book.isLike
        )
    }

    fun mapFromVolumeInfoToVolumeInfoEntity(volumeInfo: VolumeInfo): VolumeInfoEntity {
        return VolumeInfoEntity(
            title = volumeInfo.title, author = volumeInfo.author,
            imgLinks = volumeInfo.imgLinks?.let { mapFromImagesToImagesEntity(it) },
            description = volumeInfo.description,
            date = volumeInfo.date,
            previewLink = volumeInfo.previewLink
        )
    }

    fun mapFromImagesToImagesEntity(images: Images): ImagesEntity {
        return ImagesEntity(smallImg = images.smallImg)
    }

    fun mapFromBookEntityToBook(bookEntity: BookEntity): Book {
        return Book(
            mapFromVolumeInfoEntityToVolumeInfo(bookEntity.volumeInfo), bookEntity.id,
            bookEntity.isLike
        )
    }

    fun mapFromVolumeInfoEntityToVolumeInfo(volumeInfoEntity: VolumeInfoEntity): VolumeInfo {
        return VolumeInfo(
            title = volumeInfoEntity.title, author = volumeInfoEntity.author,
            imgLinks = volumeInfoEntity.imgLinks?.let { mapFromImagesEntityToImages(it) },
            description = volumeInfoEntity.description,
            date = volumeInfoEntity.date,
            previewLink = volumeInfoEntity.previewLink
        )
    }

    fun mapFromImagesEntityToImages(imagesEntity: ImagesEntity): Images {
        return Images(smallImg = imagesEntity.smallImg)
    }
}