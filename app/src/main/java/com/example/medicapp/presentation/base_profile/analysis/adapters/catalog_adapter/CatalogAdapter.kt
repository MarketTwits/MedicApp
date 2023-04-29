package com.example.medicapp.presentation.base_profile.analysis.adapters.catalog_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.medicapp.R
import com.example.medicapp.data.net.models.CatalogCloudItem
import com.example.medicapp.databinding.ItemAnalysisBinding
import com.example.medicapp.databinding.ItemCategoryBinding
import com.example.medicapp.databinding.ItemStockOrNewsBinding

class CatalogAdapter :  ListAdapter<CatalogCloudItem, CatalogViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatalogViewHolder(
        ItemAnalysisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        binding.apply {
            tvTitle.text = item.name
            tvDescription.text = item.time_result
            tvPrice.text = root.context.getString(R.string.price_rub, item.price)

        }
    }
}
class NewsDiffCallback() : DiffUtil.ItemCallback<CatalogCloudItem>(){
    override fun areItemsTheSame(oldItem: CatalogCloudItem, newItem: CatalogCloudItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: CatalogCloudItem, newItem: CatalogCloudItem): Boolean {
        return oldItem == newItem
    }
}