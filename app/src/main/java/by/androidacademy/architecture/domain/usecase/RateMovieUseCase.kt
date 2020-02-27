package by.androidacademy.architecture.domain.usecase

import by.androidacademy.architecture.domain.MoviesRepository
import by.androidacademy.architecture.domain.model.Movie

interface RateMovieUseCase {

    fun rate(movie: Movie, rating: Float)

    fun getRating(movieId: Int): Float
}

class RateMovieUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : RateMovieUseCase {

    override fun rate(movie: Movie, rating: Float) {
        moviesRepository.setMovieRating(movie.id, rating)
    }

    override fun getRating(movieId: Int): Float {
        return moviesRepository.getMovieRating(movieId)
    }
}