package com.example.axiatatest.ui.views.genrelist

import androidx.lifecycle.viewModelScope
import com.example.axiatatest.data.model.Genre
import com.example.axiatatest.data.repository.GenreRepository
import com.example.axiatatest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreListViewModel(
    private val genreRepository: GenreRepository
) : BaseViewModel() {

    fun fetchGenre() {
        viewModelScope.launch {
            runCatching {
                showLoading()
                genreRepository.fetchGenreList()
            }.onSuccess {
                withContext(Dispatchers.IO) {
                    genreRepository.saveGenreToDb(it.genres)
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