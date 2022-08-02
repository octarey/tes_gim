package com.example.test_global_indo_multimedia.repository

import androidx.lifecycle.LiveData
import com.example.test_global_indo_multimedia.models.Cart
import com.example.test_global_indo_multimedia.models.CartDAO

class CartRepository(private val cartDao:CartDAO) {

    val dataCart: LiveData<List<Cart>> = cartDao.getCart()

    suspend fun insertCart(data: Cart) =
        cartDao.insertCart(data)

    suspend fun updateCart(data: Cart) =
        cartDao.updateCart(data)

    suspend fun deleteCart(data: Cart) =
        cartDao.deleteCart(data)

}