package alidev.projects.movieexplorer.data.remote.dto

import alidev.projects.movieexplorer.domain.model.Genre
import alidev.projects.movieexplorer.domain.model.MovieDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailDto(
    val id: Int,
    val title: String,
    val overview: String,
    @param:Json(name = "poster_path")
    val posterPath: String?,
    @param:Json(name= "backdrop_path")
    val backdropPath: String?,
    @param:Json(name= "release_date")
    val releaseDate: String,
    @param:Json(name= "vote_average")
    val voteAverage: Double,
    @param:Json(name= "vote_count")
    val voteCount: Int,
    val runtime: Int?,
    val genres: List<GenreDto>,
    val status: String,
    val tagline: String?,
    val budget: Long,
    val revenue: Long,
    @param:Json(name= "original_language")
    val originalLanguage: String,
    val popularity: Double
)

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtime = runtime,
        genres = genres.map { Genre(it.id, it.name) },
        status = status,
        tagline = tagline,
        budget = budget,
        revenue = revenue,
        originalLanguage = originalLanguage,
        popularity = popularity
    )
}