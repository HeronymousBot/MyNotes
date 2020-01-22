package com.example.notes.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.notes.Models.Photo
import com.example.notes.R

import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(private val photos: List<Photo>, private val context: Context) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoThumbnailImageView = itemView.image_photoThumbnail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photos = photos[position]


    }

}