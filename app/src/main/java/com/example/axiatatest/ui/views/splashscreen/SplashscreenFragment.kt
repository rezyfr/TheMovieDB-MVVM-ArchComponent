package com.example.axiatatest.ui.views.splashscreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.axiatatest.R
import com.example.axiatatest.ui.base.BaseFragment
import com.example.axiatatest.ui.base.BaseViewModel
import com.example.axiatatest.ui.base.getNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenFragment : BaseFragment<SplashscreenViewModel>() {
    override val viewModel: SplashscreenViewModel by viewModels()

    override fun layoutRes(): Int = R.layout.fragment_splashscreen

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(1000)
            getNavController()?.navigate(SplashscreenFragmentDirections.toGenreList())
        }
    }
}