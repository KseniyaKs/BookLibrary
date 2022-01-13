package com.example.myapplicationone.dataclass.dto

import com.squareup.moshi.Json
import java.io.Serializable

data class Images (@field:Json(name = "smallThumbnail")val smallImg: String?) : Serializable