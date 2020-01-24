package com.example.notes.Fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.Adapters.AlbunsAdapter
import com.example.notes.Models.Album
import com.example.notes.R
import com.example.notes.Services.Endpoint
import com.example.notes.Services.RetrofitConfig
import kotlinx.android.synthetic.main.fragment_albums.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsFragment : Fragment() {


    fun newInstance(userId: Int?): AlbumsFragment {
        val args = Bundle()
        args.putSerializable("userId", userId)
        val fragment = AlbumsFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_albums, container, false)


        val userId = arguments!!.getSerializable("userId") as Int
        getAlbuns(userId)



        return view
    }

    fun setComponents(albums: List<Album>?) {
        val progressBar = progressBar_albumsFragment
        val albumRecyclerView = recyclerview_Albums
        val emptyTextView = textview_emptyAlbums

        if(albums != null){
           if(progressBar != null) progressBar.visibility = View.GONE
            if(emptyTextView!= null) emptyTextView.visibility = View.GONE

            albumRecyclerView.visibility = View.VISIBLE
            albumRecyclerView.adapter = AlbunsAdapter(albums, context!!)
            val layoutManager = LinearLayoutManager(context)
            albumRecyclerView.layoutManager = layoutManager
        } else{
           if(progressBar != null) progressBar.visibility = View.GONE
            recyclerview_Albums.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE

        }



    }

    fun getAlbuns(userId: Int?) {
        val retrofitClient = context?.let { RetrofitConfig.getRetrofitInstance(it) }
        val endpoint = retrofitClient!!.create(Endpoint::class.java)
        val callback = endpoint.getAlbums(userId)

        callback.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                setComponents(null)
                Toast.makeText(context, "Não conseguimos resgatar os álbuns.", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                response.body()?.forEach {
                    setComponents(response.body()!!)

                }
            }
        })
    }
}