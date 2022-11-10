package dev.wxlf.feature.details.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.wxlf.feature.details.R

class ViewPagerAdapter(private val imagesUrls: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_page, parent, false)
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Picasso.get()
            .load(imagesUrls[position])
            .placeholder(R.drawable.placeholder)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = imagesUrls.size
}