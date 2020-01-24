package com.example.notes.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.Adapters.PostsAdapter
import com.example.notes.Models.Post
import com.example.notes.R
import com.example.notes.Services.Endpoint
import com.example.notes.Services.RetrofitConfig
import kotlinx.android.synthetic.main.fragment_posts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsFragment : Fragment() {


    fun newInstance(userId: Int?): PostsFragment {
        val args = Bundle()
        args.putSerializable("userId", userId)
        val fragment = PostsFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)

        val userId = arguments!!.getSerializable("userId") as Int
        getPosts(userId)



        return view
    }

    fun setComponents(posts: List<Post>?) {
        val postsRecyclerView = recyclerview_Posts
        val progressBar = progressBar_postsFragment
        val emptyTextView = textview_emptyPosts



        if (posts != null) {
            if(progressBar != null) progressBar.visibility = View.GONE
            if(postsRecyclerView != null)postsRecyclerView.visibility = View.VISIBLE
            postsRecyclerView.adapter = PostsAdapter(posts, context!!)
            val layoutManager = LinearLayoutManager(context)
            postsRecyclerView.layoutManager = layoutManager
        } else {
            progressBar.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        }


    }

    fun getPosts(userId: Int?) {
        val retrofitClient = context?.let { RetrofitConfig.getRetrofitInstance(it) }
        val endpoint = retrofitClient!!.create(Endpoint::class.java)
        val callback = endpoint.getPosts(userId)

        callback.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                setComponents(null)
                Toast.makeText(context, "Não conseguimos resgatar os posts.", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?.forEach {
                    setComponents(response.body()!!)
                }
            }
        })
    }
}