package alidev.projects.movieexplorer.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int?,
    val genres: List<Genre>,
    val status: String,
    val tagline: String?,
    val budget: Long,
    val revenue: Long,
    val originalLanguage: String,
    val popularity: Double
)