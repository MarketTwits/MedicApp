package com.example.medicapp.presentation.base_profile.analysis.adapters.category_adapter

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffCallback() : DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.title == newItem.title
    }
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}