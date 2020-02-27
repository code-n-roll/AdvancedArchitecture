package by.androidacademy.architecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.usecase.GetMoviesByQueryUseCase
import by.androidacademy.architecture.domain.usecase.GetMoviesResult
import by.androidacademy.architecture.domain.usecase.GetPopularMoviesUseCase
import by.androidacademy.architecture.domain.usecase.RateMovieUseCase

class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMoviesByQueryUseCase: GetMoviesByQueryUseCase,
    private val rateMovieUseCase: RateMovieUseCase
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>> = _moviesLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _movieRatingLiveData = MutableLiveData<Pair<Int, Float>>()
    val movieRatingLiveData: LiveData<Pair<Int, Float>> = _movieRatingLiveData

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        getPopularMoviesUseCase.getMovies { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    _moviesLiveData.postValue(result.movies)
                }
                is GetMoviesResult.Error -> {
                    _errorLiveData.postValue(result.message)
                }
            }
        }
    }

    fun search(query: String) {
        getMoviesByQueryUseCase.getMovies(query) { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    _moviesLiveData.postValue(result.movies)
                }
                is GetMoviesResult.Error -> {
                    _errorLiveData.postValue(result.message)
                }
            }
        }
    }

    fun updateRating(position: Int, movieId: Int) {
        rateMovieUseCase.getRating(movieId).let {
            _movieRatingLiveData.postValue(Pair(position, it))
        }
    }
}