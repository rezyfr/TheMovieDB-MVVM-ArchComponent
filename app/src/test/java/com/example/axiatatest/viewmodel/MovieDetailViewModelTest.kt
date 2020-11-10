package com.example.viewmodel

import androidx.lifecycle.Observer
import com.example.BaseViewModelTest
import com.example.genre.data.model.Genre
import com.example.movielist.data.model.Review
import com.example.movielist.data.remote.response.MovieDetailResponse
import com.example.movielist.data.remote.response.ReviewResponse
import com.example.movielist.data.repository.MovieRepository
import com.example.mock
import com.example.movielist.ui.views.moviedetail.MovieDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito


class MovieDetailViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MovieDetailViewModel

    private val movieRepository = mock<MovieRepository>()

    override fun setup() {
        super.setup()
        viewModel = MovieDetailViewModel(movieRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieDetailTest() {
        testCoroutineRule.runBlockingTest {
            // Mock data
            val fakeData = mockMovieDetailResponse()

            val observer = mock<Observer<MovieDetailResponse>>()
            viewModel.movieDetail.observeForever(observer)

            Mockito.`when`(movieRepository.getMovieDetail(1)).thenReturn(fakeData)

            viewModel.getMovieDetail(1)
            // Then
            Assert.assertEquals(1, viewModel.movieDetail.value?.id)

            Mockito.verify(observer).onChanged(fakeData)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getReviewTest() {
        testCoroutineRule.runBlockingTest {
            // Mock data
            val fakeData = mockReviewResponse()

            val observer = mock<Observer<List<Review>>>()
            viewModel.reviewList.observeForever(observer)

            Mockito.`when`(movieRepository.getMovieReviews("1", 1)).thenReturn(fakeData)

            // Not done
        }
    }

    private fun mockMovieDetailResponse(): MovieDetailResponse {
        return MovieDetailResponse(
            genres = arrayListOf(com.example.genre.data.model.Genre(1, "Action")),
            id = 1,
            overview = "Lorem ipsum dolor",
            title = "The Movie Detail"
        )
    }

    private fun mockReviewResponse(): ReviewResponse {
        val review = ReviewResponse()
        val review1 = Review(id = "1")
        val review2 = Review(id = "2")
        val review3 = Review(id = "3")
        val review4 = Review(id = "4")
        val review5 = Review(id = "5")
        review.results = arrayListOf(review1, review2, review3, review4, review5)
        return review
    }
}