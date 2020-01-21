package com.example.notes.Models

import com.google.gson.annotations.SerializedName

class Album {
    @SerializedName("userId")
    var userId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null

    constructor(userId: Int?, id: Int?, title: String?) {
        this.userId = userId
        this.id = id
        this.title = title
    }
}