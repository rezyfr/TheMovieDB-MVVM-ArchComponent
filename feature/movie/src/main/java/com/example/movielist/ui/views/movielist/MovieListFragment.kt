package com.example.movielist.ui.views.movielist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.movielist.data.model.Movie
import com.example.base.views.BaseFragment
import com.example.genre.data.model.Genre
import com.example.movielist.R
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment<MovieListViewModel>() {

    override fun layoutRes(): Int = R.layout.movie_list_fragment

    override val viewModel: MovieListViewModel by viewModel()

//    private val args: MovieListFragmentArgs by navArgs()
    private lateinit var args: Genre

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.getParcelable("genre") ?: Genre()
        viewModel.genreId.value = args.id
        movieListAdapter = MovieListAdapter {
            toMovieDetail(it)
        }
        rv_movie.adapter = movieListAdapter
        viewModel.apply {
            movieList.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(it)
            })
        }
    }

    private fun toMovieDetail(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.movie_detail_fragment, bundle)
//        getNavController()?.navigate(MovieListFragmentDirections.toMovieDetail(movie))
    }
}