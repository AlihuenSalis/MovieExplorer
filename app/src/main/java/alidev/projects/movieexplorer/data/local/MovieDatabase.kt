package alidev.projects.movieexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import alidev.projects.movieexplorer.data.local.dao.MovieDao
import alidev.projects.movieexplorer.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}