package com.example.movielist.ui.views.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.base.views.BaseFragment
import com.example.base.views.getNavController
import com.example.base.utils.toDateView
import com.example.base.utils.toUsd
import com.example.movielist.R
import com.example.movielist.data.model.Movie
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<MovieDetailViewModel>() {
    override val viewModel: MovieDetailViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.movie_detail_fragment

//    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var args: Movie

    private val reviewAdapter by lazy {
        ReviewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.getParcelable("movie") ?: Movie()
        viewModel.getMovieDetail(args.id)
        viewModel.getTrailerDetail(args.id)
        viewModel.movieId.value = args.id
        viewModel.movieDetail.observe(viewLifecycleOwner, {
            val rating = it.vote_average?.div(2)
            Glide.with(this)
                .load(it.getBackdropPath().toString())
                .into(iv_backdrop)
            tv_title.text = it.title
            tv_overview.text = it.overview
            tv_genre.text = it.genres?.get(0)?.name ?: ""
            tv_runtime.text = "${it.runtime} minutes"
            tv_budget.text = it.budget?.toUsd() ?: ""
            tv_date.text = it.release_date?.toDateView()
            rating_bar.rating = rating?.toFloat() ?: 0.0.toFloat()
        })
        viewModel.trailerKey.observe(viewLifecycleOwner, { trailer ->
            if (trailer != null) {
                iv_play.visibility = View.VISIBLE
            }
            iv_play.setOnClickListener {
                val goToTrailer = Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getYoutubeURL()))
                startActivity(goToTrailer)
            }
        })
        rv_review.adapter = reviewAdapter
        rv_review.isNestedScrollingEnabled = false
        viewModel.apply {
            reviewList.observe(viewLifecycleOwner, {
                reviewAdapter.submitList(it)
            })
        }
        iv_back.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }
}