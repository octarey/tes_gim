package com.example.test_global_indo_multimedia.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.test_global_indo_multimedia.models.Cart
import com.example.test_global_indo_multimedia.repository.CartRepository
import com.example.test_global_indo_multimedia.service.RoomDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application): AndroidViewModel(application) {

    val repository: CartRepository
    var readAll : LiveData<List<Cart>>
    init {
        val cartDB = RoomDataBase.getDatabase(application).getCartDAO()
        repository = CartRepository(cartDB)
        readAll = repository.dataCart

    }

    fun addCart(cart: Cart){
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertCart(cart)
        }
    }

    fun deleteCart(cart: Cart){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteCart(cart)
        }
    }
}