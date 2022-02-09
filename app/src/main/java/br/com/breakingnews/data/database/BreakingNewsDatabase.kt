package br.com.breakingnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.breakingnews.data.database.models.BreakingNewsObject

@Database(
    entities = [BreakingNewsObject::class],
    version = 1,
    exportSchema = false
)
abstract class BreakingNewsDatabase : RoomDatabase() {

    abstract fun getBreakingNewsDao(): BreakingNewsDao
}