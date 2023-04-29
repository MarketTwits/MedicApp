package com.petrs.smartlab.ui.fragments.main.analyzes.news_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.medicapp.R
import com.example.medicapp.data.net.models.NewsCloudItem
import com.example.medicapp.databinding.ItemStockOrNewsBinding

class NewsAdapter :  ListAdapter<NewsCloudItem, NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        ItemStockOrNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        binding.apply {
            tvTitle.text = item.name
            tvDescription.text = item.description
            tvPrice.text = root.context.getString(R.string.price_rub, item.price)
            Glide.with(ivPhoto)
                .load(item.image)
                .into(ivPhoto)
        }
    }
}
class NewsDiffCallback() : DiffUtil.ItemCallback<NewsCloudItem>(){
    override fun areItemsTheSame(oldItem: NewsCloudItem, newItem: NewsCloudItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: NewsCloudItem, newItem: NewsCloudItem): Boolean {
        return oldItem == newItem
    }
}