package com.example.myapplicationone.dataClass

import com.squareup.moshi.Json
import java.io.Serializable

data class Images (@field:Json(name = "smallThumbnail")val smallImg: String?) : Serializable