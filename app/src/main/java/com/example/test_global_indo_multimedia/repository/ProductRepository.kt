package com.example.test_global_indo_multimedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_global_indo_multimedia.models.Product
import com.example.test_global_indo_multimedia.models.ServiceResponse
import com.example.test_global_indo_multimedia.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImp(private val apiService: ApiService): ProductRepository{
    private val _liveData: MutableLiveData<ServiceResponse> by lazy {
        MutableLiveData<ServiceResponse>()
    }

    override fun getProducts(): LiveData<ServiceResponse> {
        apiService.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                _liveData.value = ServiceResponse(
                    isSuccess = response.isSuccessful,
                    message = response.message(),
                    data = response.body(),
                    code = response.code()
                )
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                _liveData.value = ServiceResponse(isSuccess = false, message = t.localizedMessage)
            }

        })
        return _liveData
    }
}
interface ProductRepository {
    fun getProducts() : LiveData<ServiceResponse>
}