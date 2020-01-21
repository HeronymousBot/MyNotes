package com.example.notes.Models

import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("albumId")
    var albumId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = null
}