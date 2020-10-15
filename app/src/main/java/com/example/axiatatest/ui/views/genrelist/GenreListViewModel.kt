package com.example.axiatatest.ui.views.genrelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.axiatatest.data.model.Genre
import com.example.axiatatest.data.repository.GenreRepository
import com.example.axiatatest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GenreListViewModel(
    private val genreRepository: GenreRepository
) : BaseViewModel() {

    val genreList = MutableLiveData<List<Genre>>()

    fun fetchGenre() {
        viewModelScope.launch {
            runCatching {
                showLoading()
                genreRepository.fetchGenreList()
            }.onSuccess {
                it.genres?.let { genres ->
                    withContext(Dispatchers.IO) {
                        Timber.d("GENRESAVEDTODB")
                        genreRepository.saveGenreToDb(genres)
                    }
                    genreList.value = genres
                }
                hideLoading()
            }.onFailure {
                onError(it)
            }
        }
    }

    suspend fun getGenreList(): List<Genre>? {
        return genreRepository.getGenreList()
    }

}