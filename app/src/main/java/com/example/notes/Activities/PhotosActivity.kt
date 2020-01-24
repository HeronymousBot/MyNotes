package com.example.notes.Activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapters.AlbunsAdapter
import com.example.notes.Adapters.PhotosAdapter
import com.example.notes.Models.Photo
import com.example.notes.R
import com.example.notes.Services.Endpoint
import com.example.notes.Services.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivity : AppCompatActivity() {
    var userId: Int? = null
    var albumId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        val sharedPref: SharedPreferences = getSharedPreferences("shared_preferences", 0)

        userId = sharedPref.getString("userId", "0")!!.toInt()
        albumId = intent.getIntExtra("albumId", 0)
        val toolbar: Toolbar = findViewById(R.id.toolbar_PhotosActivity)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)




        this.title = "Photos of album $albumId"

        getPhotos(albumId, userId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setComponents(photos: List<Photo>?) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar_photosActivity)

        val photosRecyclerView: RecyclerView = findViewById(R.id.recyclerview_PhotosActivity)
        val emptyTextView: TextView = findViewById(R.id.textview_emptyPhotos)


        if (photos.isNullOrEmpty()) {
            progressBar.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
            photosRecyclerView.adapter = PhotosAdapter(photos, applicationContext)
            val layoutManager = GridLayoutManager(applicationContext, 2)
            photosRecyclerView.layoutManager = layoutManager

        }


    }

    fun getPhotos(userId: Int?, albumId: Int?) {
        val retrofitClient = RetrofitConfig.getRetrofitInstance(applicationContext)
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPhotos(userId, albumId)

        callback.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                setComponents(null)
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                response.body()?.forEach {
                    setComponents(response.body()!!)
                }
            }
        })
    }
}
