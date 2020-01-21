package com.example.notes.Models

import com.google.gson.annotations.SerializedName

class Todo {
    @SerializedName("userId")
    var userId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("completed")
    var completed: Boolean? = null

    constructor(userId: Int?, id: Int?, title: String?, completed: Boolean?) {
        this.userId = userId
        this.id = id
        this.title = title
        this.completed = completed
    }
}