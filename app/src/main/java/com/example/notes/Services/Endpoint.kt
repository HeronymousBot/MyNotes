package com.example.notes.Services

import com.example.notes.Models.Photo
import com.example.notes.Models.Post
import com.example.notes.Models.Todo
import com.example.notes.Models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    //User related
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int?): Call<User>

    //Post related
    @GET("posts")
    fun getPosts(@Query("userId") userId: Int?): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostDetails(@Query("userId") userId: Int?,
                       @Path("id") id: Int?): Call<Post>

    //Album related
    @GET("albums")
    fun getAlbums(@Query("userId") userId: Int?)

    //Photo related
    @GET("photos")
    fun getPhotos(@Query("userId") userId: Int?,
                  @Query("albumId") albumId: Int?): Call<List<Photo>>

    @GET("photos/{id}")
    fun getPhotosDetails(@Query("userId") userId: Int?,
                         @Query("albumId") albumId: Int?,
                         @Path("id") id: Int?): Call<Photo>

    //Todos related
    @GET("todos")
    fun getTodos(@Query("userId") userId: Int?) : Call<List<Todo>>

    @GET("todos/{id}")
    fun getTodosDetails(@Query("userId") userId:Int?, @Path("id") id :Int?)

}