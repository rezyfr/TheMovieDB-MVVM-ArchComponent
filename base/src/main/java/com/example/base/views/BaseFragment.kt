package com.example.base.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.data.R
import com.example.base.utils.hideLoading
import com.example.base.utils.showLoading

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {

    protected abstract val viewModel: ViewModel

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer {
                handleLoading(it == true)
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(it)
            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(getString(R.string.no_internet_connection))
            })
            connectTimeoutEvent.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(getString(R.string.connect_timeout))
            })
        }
    }

    open fun handleLoading(isLoading: Boolean) {
        if (isLoading) context?.showLoading() else hideLoading()
    }

    fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return

        hideLoading()

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.getNavController(): NavController? {
    return try {
        NavHostFragment.findNavController(this)
    } catch (e: IllegalStateException) {
        null
    }
}