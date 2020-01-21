package com.example.notes.Models

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("userId")
    var userId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("body")
    var body: String? = null

    constructor(userId: Int?, id: Int?, title: String?, body: String?) {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }
}