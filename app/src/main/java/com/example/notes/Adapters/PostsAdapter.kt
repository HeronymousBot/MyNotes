package com.example.notes.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Models.Post
import com.example.notes.R
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(private val posts : List<Post>, private val context : Context) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.textview_postTitle
        val bodyTextView = itemView.textview_postBody

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder?.let{
            holder.titleTextView.text = post.title
            holder.bodyTextView.text = post.body
        }



    }

}