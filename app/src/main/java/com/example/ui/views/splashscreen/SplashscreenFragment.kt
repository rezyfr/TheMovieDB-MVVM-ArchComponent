package com.example.ui.views.splashscreen

import com.example.R
import com.example.base.views.BaseFragment
import com.example.base.views.getNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashscreenFragment : BaseFragment<SplashscreenViewModel>() {
    override val viewModel: SplashscreenViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_splashscreen

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(1000)
            getNavController()?.navigate(SplashscreenFragmentDirections.toGenreList())
        }
    }
}