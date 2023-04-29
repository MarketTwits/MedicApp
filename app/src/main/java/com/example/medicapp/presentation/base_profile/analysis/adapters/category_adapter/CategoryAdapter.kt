package com.example.medicapp.presentation.base_profile.analysis.adapters.category_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.medicapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val eventListener: CategoryEventListener
) : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        eventListener
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (position == 0) holder.selectItem()
    }
}
data class Category(
    val title: String
)