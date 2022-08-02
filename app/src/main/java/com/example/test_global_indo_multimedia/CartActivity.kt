package com.example.test_global_indo_multimedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_global_indo_multimedia.adapters.CartAdapter
import com.example.test_global_indo_multimedia.viewmodels.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter : CartAdapter
    private lateinit var cartRV : RecyclerView
    private lateinit var cartTotal : TextView
    private lateinit var cartBack : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRV = findViewById(R.id.cart_rv)
        cartTotal = findViewById(R.id.cart_total)
        cartBack = findViewById(R.id.cart_backBtn)

        cartViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(CartViewModel::class.java)

        cartViewModel.readAll.observe(this, Observer {
            Log.d("room tes",it.toString())
            cartAdapter = CartAdapter(this,it,cartViewModel)
            cartRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            cartRV.adapter = cartAdapter

            cartTotal.text = "$" + it.sumByDouble { it.price.toDouble() }.toFloat().toString()

        })

        cartBack.setOnClickListener {
            val intent = Intent(this@CartActivity, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}