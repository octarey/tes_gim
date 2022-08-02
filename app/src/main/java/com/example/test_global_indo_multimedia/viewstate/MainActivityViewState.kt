package com.example.test_global_indo_multimedia.viewstate

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class MainActivityViewState : BaseObservable() {
    @Bindable
    var shrimmerVisibility = View.VISIBLE

    @Bindable
    var recyclerViewVisibility = View.VISIBLE

    @Bindable
    var errorMessageVisibility = View.GONE

    @Bindable
    var errorMessageText: String? = null

    var apiInProgress = true
        set(value) {
            field = value
            if (field) {
                shrimmerVisibility = View.VISIBLE
                recyclerViewVisibility = View.GONE
                errorMessageVisibility = View.GONE
            } else {
                recyclerViewVisibility = View.VISIBLE
                shrimmerVisibility = View.GONE
                errorMessageVisibility = View.GONE
            }
            notifyChange()
        }


    fun setError(errorText: String) {
        errorMessageVisibility = View.VISIBLE
        errorMessageText = errorText
        notifyChange()
    }
}