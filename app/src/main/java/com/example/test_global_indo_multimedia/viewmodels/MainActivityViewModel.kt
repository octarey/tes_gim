package com.example.test_global_indo_multimedia.viewmodels

import androidx.lifecycle.*
import com.example.test_global_indo_multimedia.models.ServiceResponse
import com.example.test_global_indo_multimedia.repository.ProductRepository

class MainActivityViewModel(private val repository: ProductRepository) : ViewModel(){
    private val query = MutableLiveData<String>()
    val productsLiveData: LiveData<ServiceResponse> = Transformations.switchMap(query, ::getProductFromRepo)

    private fun getProductFromRepo(pageNo: String) = repository.getProducts()

    fun getProducts() = apply { query.value = "" }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ProductRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(repository) as T
        }

    }
}