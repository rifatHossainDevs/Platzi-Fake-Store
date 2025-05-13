package com.epsports.platzifakestore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.epsports.platzifakestore.databinding.ActivityMainBinding
import com.epsports.platzifakestore.recyclerView.Adapter
import com.epsports.platzifakestore.recyclerView.AdapterProduct
import com.epsports.platzifakestore.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapterCategory: Adapter
    lateinit var adapterProduct: AdapterProduct
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeCategories()
        observeProducts()
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
                adapterCategory = Adapter(it)
                binding.rvLayoutCategories.adapter = adapterCategory
            }
        }
    }
}
