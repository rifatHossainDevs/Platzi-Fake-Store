package com.epsports.platzifakestore.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epsports.platzifakestore.Nodes
import com.epsports.platzifakestore.databinding.ActivityMainBinding
import com.epsports.platzifakestore.recyclerView.Adapter
import com.epsports.platzifakestore.recyclerView.AdapterProduct
import com.epsports.platzifakestore.recyclerView.AdapterProductByCategory
import com.epsports.platzifakestore.viewModel.HomeViewModel

class MainActivity : AppCompatActivity(), Adapter.HandleClickListener,
    AdapterProductByCategory.HandleClickListener, AdapterProduct.HandleClickListener {
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
                adapterProductByCategory = AdapterProductByCategory(it, this@MainActivity)
                binding.rvLayoutProductByCategory.adapter = adapterProductByCategory
            }
        }
    }

    private fun observeProducts() {
        homeViewModel.products.observe(this) { productList ->
            productList?.let {
                adapterProduct = AdapterProduct(it, this)
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

    override fun getProductTitle(title: String) {
        if (title.isNotEmpty()) {
            val productDetailIntent = Intent(this@MainActivity, ProductDetails::class.java)
            productDetailIntent.putExtra(Nodes.TITLE, title)
            startActivity(productDetailIntent)
        }else{
            Toast.makeText(this, "Title is Empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getTitle(title: String) {
        if (title.isNotEmpty()){
            val productDetailIntent = Intent(this@MainActivity, ProductDetails::class.java)
            productDetailIntent.putExtra(Nodes.TITLE, title)
            startActivity(productDetailIntent)
        }else{
            Toast.makeText(this, "Title is Empty", Toast.LENGTH_SHORT).show()
        }
    }
}
