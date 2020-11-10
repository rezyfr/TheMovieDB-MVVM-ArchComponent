package com.example.genre.genrelist

import android.os.Bundle
import android.view.View
import com.example.base.views.BaseFragment
import com.example.genre.R
import kotlinx.android.synthetic.main.genre_list_fragment.*
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.genre.data.model.Genre
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class GenreListFragment : BaseFragment<GenreListViewModel>() {

    private lateinit var genreAdapter: GenreAdapter
    override val viewModel: GenreListViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.genre_list_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchGenre()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genreAdapter = GenreAdapter(arrayListOf()) {
            goToMovieList(it)
        }
        rv_genre.adapter = genreAdapter
        lifecycleScope.launch(Dispatchers.Main) {
            val genreList = viewModel.getGenreList()
            Timber.d("SETGENRE")
            if (genreList != null) {
                genreAdapter.setData(genreList)
            }
        }
        viewModel.genreList.observe(viewLifecycleOwner, {
            if (it != null) genreAdapter.setData(it)
        })
    }

    private fun goToMovieList(genre: Genre) {
        val bundle = Bundle()
        bundle.putParcelable("genre", genre)
        findNavController().navigate(R.id.movie_list_fragment, bundle)
//        getNavController()?.navigate(GenreListFragmentDirections.toMovieListFragment(genre))
    }
}