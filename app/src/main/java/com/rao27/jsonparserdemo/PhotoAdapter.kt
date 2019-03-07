package com.rao27.jsonparserdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rao27.jsonparserdemo.model.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item_view, parent, false) as View)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.photoView.findViewById<ImageView>(R.id.photo_img)?.also {
            Picasso.get()
                .load(photo.thumbnailUrl)
                .into(it)
        }
        holder.photoView.findViewById<TextView>(R.id.photo_title)?.also {
            it.text = photo.title
        }
    }

    class PhotoViewHolder(val photoView: View) : RecyclerView.ViewHolder(photoView)
}