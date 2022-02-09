package br.com.breakingnews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.breakingnews.data.database.models.BreakingNewsObject
import kotlinx.coroutines.flow.Flow

@Dao
interface BreakingNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreakingNews(breakingNewsObject: BreakingNewsObject)

    @Query("SELECT * FROM breaking_news_table ORDER BY title DESC")
    fun getAllBreakingNews(): Flow<List<BreakingNewsObject>>

    @Query("DELETE FROM breaking_news_table")
    suspend fun deleteAll()
}