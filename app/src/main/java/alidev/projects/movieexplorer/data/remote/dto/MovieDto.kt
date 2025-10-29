package alidev.projects.movieexplorer.data.remote.dto

import alidev.projects.movieexplorer.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "genre_ids") val genreIds: List<Int>
)

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = "https://image.tmdb.org/t/p/w500" + posterPath,
        backdropPath = "https://image.tmdb.org/t/p/w500" + backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        adult = adult,
        genreIds = genreIds
    )
}
