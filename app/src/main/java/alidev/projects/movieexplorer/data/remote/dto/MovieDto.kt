package alidev.projects.movieexplorer.data.remote.dto

import alidev.projects.movieexplorer.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @param:Json(name = "id") val id: Int,
    @param:Json(name = "title") val title: String,
    @param:Json(name = "overview") val overview: String,
    @param:Json(name = "poster_path") val posterPath: String?,
    @param:Json(name = "backdrop_path") val backdropPath: String?,
    @param:Json(name = "release_date") val releaseDate: String,
    @param:Json(name = "vote_average") val voteAverage: Double,
    @param:Json(name = "vote_count") val voteCount: Int,
    @param:Json(name = "popularity") val popularity: Double,
    @param:Json(name = "adult") val adult: Boolean,
    @param:Json(name = "genre_ids") val genreIds: List<Int>
)

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        adult = adult,
        genreIds = genreIds
    )
}
