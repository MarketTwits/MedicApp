package com.example.medicapp.presentation.base_profile.analysis.adapters.category_adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medicapp.R
import com.example.medicapp.databinding.ItemCategoryBinding


class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
    private val eventListener: CategoryEventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.apply {
            root.setOnClickListener {
                eventListener.onCategoryClick(category)

                selectItem()
            }

            tvCategory.text = category.title
        }
    }
    fun selectItem() {
        binding.apply {
            root.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.second_blue))
            tvCategory.setTextColor(ContextCompat.getColor(root.context, R.color.white))
        }
    }

    fun deselectItem() {
        binding.apply {
            root.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.grey_F5))
            tvCategory.setTextColor(ContextCompat.getColor(root.context, R.color.grey_7E))
        }
    }
}