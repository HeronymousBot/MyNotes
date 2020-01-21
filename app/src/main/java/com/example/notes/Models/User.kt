package com.example.notes.Models

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id:Int? = null
    @SerializedName("name")
    var name:String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("website")
    var website:String? = null

    constructor(id: Int?, name: String?, email: String?, phone: String?, website: String?) {
        this.id = id
        this.name = name
        this.email = email
        this.phone = phone
        this.website = website
    }
}