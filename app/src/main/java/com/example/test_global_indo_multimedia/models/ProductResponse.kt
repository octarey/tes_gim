package com.example.test_global_indo_multimedia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Parcelize
//data class ProductResponse(
//    val products: List<Product>
//) : Parcelable

@Parcelize
data class Product(
    val id : Int = 0,
    val title: String? = null,
    val price: Float = 0F,
    val description: String? = null,
    val category: String? = null,
    val image: String? = null,
    val rating: Rating,
) : Parcelable

@Parcelize
data class Rating(
    val rate: Float = 0F,
    val count: Int = 0
) : Parcelable

data class ServiceResponse(
    val data: List<Product>? = null,
    val code: Int = 0,
    val message: String,
    val isSuccess: Boolean = true
)

