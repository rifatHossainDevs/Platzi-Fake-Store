package com.epsports.platzifakestore.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.platzifakestore.databinding.CategoryItemLayoutBinding
import com.epsports.platzifakestore.model.ResponseCategories

class Adapter(val categoryList: ResponseCategories) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val category = categoryList[position]
        holder.binding.apply {
            ivLogo.load(category.image)
            tvCategoryName.text = category.name
        }

    }

    override fun getItemCount(): Int = categoryList.size


    class ViewHolder(val binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}