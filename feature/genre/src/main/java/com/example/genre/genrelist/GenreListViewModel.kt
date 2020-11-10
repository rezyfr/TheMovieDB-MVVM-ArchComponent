package com.example.genre.genrelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.views.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GenreListViewModel(
    private val genreRepository: com.example.genre.data.repository.GenreRepository
) : BaseViewModel() {

    val genreList = MutableLiveData<List<com.example.genre.data.model.Genre>>()

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

    suspend fun getGenreList(): List<com.example.genre.data.model.Genre>? {
        return genreRepository.getGenreList()
    }

}