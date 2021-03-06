package com.example.notes.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.notes.Activities.PhotosActivity
import com.example.notes.Models.Album
import com.example.notes.R
import kotlinx.android.synthetic.main.item_album.view.*

class AlbunsAdapter(private val albuns : List<Album>, private val context : Context) : Adapter<AlbunsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.textview_albumTitle
        val  albumCardView = itemView.cardview_Album
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albuns.size
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albuns[position]

        holder.titleTextView.text = album.title


        holder.albumCardView.setOnClickListener{
            val photosIntent = Intent(context, PhotosActivity::class.java)

            photosIntent.putExtra("albumId", album.id)
            context.startActivity(photosIntent)

        }


    }

}