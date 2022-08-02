package com.example.test_global_indo_multimedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_global_indo_multimedia.adapters.ProductAdapter
import com.example.test_global_indo_multimedia.databinding.ActivityMainBinding
import com.example.test_global_indo_multimedia.repository.ProductRepository
import com.example.test_global_indo_multimedia.repository.ProductRepositoryImp
import com.example.test_global_indo_multimedia.service.RetrofitFactory
import com.example.test_global_indo_multimedia.viewmodels.CartViewModel
import com.example.test_global_indo_multimedia.viewmodels.MainActivityViewModel
import com.example.test_global_indo_multimedia.viewstate.MainActivityViewState

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewState: MainActivityViewState
    private lateinit var productAdapter: ProductAdapter
    private lateinit var btnCart : ImageButton

    private val repository : ProductRepository by lazy {
        ProductRepositoryImp(RetrofitFactory.service)
    }

    private val viewModel: MainActivityViewModel by lazy {
        val factory = MainActivityViewModel.Factory(repository = repository)
        ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cartViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CartViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainRv.layoutManager = LinearLayoutManager(this)
        viewState = MainActivityViewState()
        binding.viewState = viewState


        viewModel.productsLiveData.observe(this, Observer {
            viewState.apiInProgress = false
            if (it.isSuccess){
                Log.d("tes",it.code.toString())
                val mlayoutManager = GridLayoutManager(this,2)
                productAdapter = ProductAdapter(this@MainActivity,it?.data,cartViewModel)
                binding.mainRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding.mainRv.layoutManager = mlayoutManager
                binding.mainRv.adapter = productAdapter
            }else{
                viewState.setError(it.message)
                Log.d("tes error",it.message.toString())
            }
        })

        viewModel.getProducts()

        btnCart = findViewById(R.id.main_cart)
        btnCart.setOnClickListener {
            Toast.makeText(this, "ke keranjang", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}