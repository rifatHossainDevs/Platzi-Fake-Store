package com.epsports.platzifakestore.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.platzifakestore.databinding.CategoryItemLayoutBinding
import com.epsports.platzifakestore.model.Category

class AdapterProduct(private val categoryList: List<Category>?) :
    RecyclerView.Adapter<AdapterProduct.ViewHolder>() {

    class ViewHolder(val binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList?.get(position)
        holder.binding.apply {
            ivLogo.load(category?.image)
            tvCategoryName.text = category?.name
        }
    }

    override fun getItemCount(): Int = categoryList?.size ?: 0
}
