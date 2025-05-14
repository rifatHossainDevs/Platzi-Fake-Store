package com.epsports.platzifakestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.epsports.platzifakestore.databinding.ActivityProductDetailsBinding
import com.epsports.platzifakestore.viewModel.HomeViewModel

class ProductDetails : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(Nodes.TITLE)

        if (title != null) {
            viewModel.getProductByTitle(title)
        }

        observeListener()

    }

    @SuppressLint("SetTextI18n")
    private fun observeListener() {
        viewModel.productsByTitle.observe(this) { productDetails ->
            productDetails.firstOrNull().let {
                with(binding) {
                    ivProductImage.load(it?.images?.get(0))
                    tvProductName.text = it?.title
                    tvProductPrice.text = "৳ ${it?.price}"
                    tvDescription.text = it?.description
                    tvProductCategory.text = it?.category?.name
                }
            }

        }
    }
}