package com.example.axiatatest.ui.views.genrelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.axiatatest.R
import com.example.axiatatest.data.model.Genre
import com.example.axiatatest.ui.base.BaseFragment
import com.example.axiatatest.ui.base.getNavController
import kotlinx.android.synthetic.main.genre_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreListFragment : BaseFragment<GenreListViewModel>() {

    private lateinit var genreAdapter: GenreAdapter
    override val viewModel: GenreListViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.genre_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchGenre()
        genreAdapter = GenreAdapter(arrayListOf()){
            goToMovieList(it)
        }
        rv_genre.adapter = genreAdapter
        lifecycleScope.launch(Dispatchers.IO) {
            val genreList = viewModel.getGenreList()
            if (genreList != null) {
                genreAdapter.setData(genreList)
            }
        }
    }

    private fun goToMovieList(genre: Genre) {
        getNavController()?.navigate(GenreListFragmentDirections.toMovieListFragment(genre))
    }
}