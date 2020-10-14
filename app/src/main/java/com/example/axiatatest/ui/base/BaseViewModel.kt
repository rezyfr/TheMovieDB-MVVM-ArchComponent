package com.example.axiatatest.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.axiatatest.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel(){

    val isLoadMore = MutableLiveData(false)

    val isLoading by lazy { MutableLiveData(false) }
    val errorMessage by lazy { SingleLiveEvent<String>() }
    val noInternetConnectionEvent by lazy { SingleLiveEvent<Unit>() }
    val connectTimeoutEvent by lazy { SingleLiveEvent<Unit>() }

    open val firstPage = 1

    open suspend fun onError(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            when (throwable) {
                is UnknownHostException -> {
                    noInternetConnectionEvent.call()
                }
                is ConnectException -> {
                    noInternetConnectionEvent.call()
                }
                is SocketTimeoutException -> {
                    connectTimeoutEvent.call()
                }
            }
            hideLoading()
        }
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}