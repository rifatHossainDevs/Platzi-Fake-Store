package com.epsports.platzifakestore.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.epsports.platzifakestore.databinding.CategoryItemLayoutBinding
import com.epsports.platzifakestore.databinding.ProductItemLayoutBinding
import com.epsports.platzifakestore.model.Category
import com.epsports.platzifakestore.model.Products

class AdapterProduct(private val productList: List<Products>?) :
    RecyclerView.Adapter<AdapterProduct.ViewHolder>() {

    class ViewHolder(val binding: ProductItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = productList?.get(position)
        holder.binding.apply {
            ivImage.load(category?.images?.get(0))
            tvProductName.text = category?.title
            tvProductPrice.text = "$ ${category?.price}"
            tvProductCategory.text = category?.category?.name
        }
    }

    override fun getItemCount(): Int = productList?.size ?: 0
}
