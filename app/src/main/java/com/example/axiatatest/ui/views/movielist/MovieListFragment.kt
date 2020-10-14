package com.example.axiatatest.ui.views.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.axiatatest.R
import com.example.axiatatest.data.model.Movie
import com.example.axiatatest.ui.base.BaseFragment
import com.example.axiatatest.ui.base.getNavController
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieListFragment : BaseFragment<MovieListViewModel>() {

    override fun layoutRes(): Int = R.layout.movie_list_fragment

    override val viewModel: MovieListViewModel by viewModel()

    private val args: MovieListFragmentArgs by navArgs()

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.genreId.value = args.genre.id
        movieListAdapter = MovieListAdapter {
            toMovieDetail(it)
        }
        rv_movie.adapter = movieListAdapter
        viewModel.apply {
            itemList.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(it)
            })
        }
    }

    private fun toMovieDetail(movie: Movie) {
        Timber.d("Movie clicked")
        getNavController()?.navigate(MovieListFragmentDirections.toMovieDetail(movie))
    }
}