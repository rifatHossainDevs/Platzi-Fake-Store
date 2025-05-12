package com.epsports.platzifakestore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.epsports.platzifakestore.databinding.ActivityMainBinding
import com.epsports.platzifakestore.recyclerView.Adapter
import com.epsports.platzifakestore.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

observerListener()

    }

    private fun observerListener() {
homeViewModel.categories.observe(this) {
try {
it.let {
    adapter = Adapter(it)
}
}
}
    }
}