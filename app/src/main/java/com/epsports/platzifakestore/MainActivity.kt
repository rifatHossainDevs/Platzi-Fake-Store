package com.epsports.platzifakestore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.epsports.platzifakestore.databinding.ActivityMainBinding
import com.epsports.platzifakestore.model.ProductByCategory
import com.epsports.platzifakestore.recyclerView.Adapter
import com.epsports.platzifakestore.recyclerView.AdapterProduct
import com.epsports.platzifakestore.recyclerView.AdapterProductByCategory
import com.epsports.platzifakestore.viewModel.HomeViewModel

class MainActivity : AppCompatActivity(), Adapter.HandleClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapterCategory: Adapter
    lateinit var adapterProduct: AdapterProduct
    lateinit var adapterProductByCategory: AdapterProductByCategory
    lateinit var categorySlug: String
    private val homeViewModel: HomeViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeCategories()
        observeProducts()
        observeProductsByCategory()

        binding.tvAllProducts.setOnClickListener {
            binding.tvAllProducts.visibility = View.GONE
            binding.tvCategoryTitle.text = "All Products"
            binding.rvLayoutProduct.visibility = View.VISIBLE
            binding.rvLayoutProductByCategory.visibility = View.GONE
        }
    }

    private fun observeProductsByCategory() {
        homeViewModel.productsByCategory.observe(this) { productByCategory ->
            productByCategory?.let {
                adapterProductByCategory = AdapterProductByCategory(it)
                binding.rvLayoutProductByCategory.adapter = adapterProductByCategory
            }
        }
    }

    private fun observeProducts() {
        homeViewModel.products.observe(this) { productList ->
            productList?.let {
                adapterProduct = AdapterProduct(it)
                binding.rvLayoutProduct.adapter = adapterProduct
            }
        }
    }

    private fun observeCategories() {
        homeViewModel.categories.observe(this) { categoryList ->
            categoryList?.let {
                adapterCategory = Adapter(it, this@MainActivity)
                binding.rvLayoutCategories.adapter = adapterCategory
            }
        }
    }

    override fun getCategoryName(categoryName: String) {
        categorySlug = categoryName
        binding.tvCategoryTitle.text = categorySlug
        binding.rvLayoutProduct.visibility = View.GONE
        binding.rvLayoutProductByCategory.visibility = View.VISIBLE
        binding.tvAllProducts.visibility = View.VISIBLE
        homeViewModel.getProductByCategory(categorySlug)
    }
}
