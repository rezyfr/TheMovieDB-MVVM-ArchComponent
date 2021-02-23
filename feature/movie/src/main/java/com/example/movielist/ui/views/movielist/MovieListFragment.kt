package com.example.movielist.ui.views.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.base.views.BaseFragment
import com.example.genre.data.model.Genre
import com.example.movielist.R
import com.example.movielist.data.model.Movie
import kotlinx.android.synthetic.main.movie_list_fragment.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment<MovieListViewModel>() {

    override fun layoutRes(): Int = R.layout.movie_list_fragment

    override val viewModel: MovieListViewModel by viewModel()

    //    private val args: MovieListFragmentArgs by navArgs()
    private lateinit var args: Genre

    private lateinit var movieListAdapter: MovieListAdapter
    private var coroutineJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args = arguments?.getParcelable("genre") ?: Genre()

        movieListAdapter = MovieListAdapter {
            toMovieDetail(it)
        }

        rv_movie.adapter = movieListAdapter

        coroutineJob?.cancel()

        coroutineJob = lifecycleScope.launch {
            viewModel.genreId.value = args.id
            viewModel.getMovieList().collect {
                movieListAdapter.submitData(it)
            }
        }

        movieListAdapter.addLoadStateListener { loadState ->

            /*
            * loadState.refresh - represents the load state for loading the PagingData for the first time.
              loadState.prepend - represents the load state for loading data at the start of the list.
              loadState.append - represents the load state for loading data at the end of the list.
            * */

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                handleLoading(true)
            else {
                handleLoading(false)

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    handleErrorMessage(it.error.toString())
                }
            }
        }
    }

    private fun toMovieDetail(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", movie)
        findNavController().navigate(R.id.movie_detail_fragment, bundle)
//        getNavController()?.navigate(MovieListFragmentDirections.toMovieDetail(movie))
    }
}