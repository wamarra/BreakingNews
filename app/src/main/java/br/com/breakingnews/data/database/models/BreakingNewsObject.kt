package br.com.breakingnews.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "breaking_news_table")
data class BreakingNewsObject constructor(
    @PrimaryKey
    var id: String,
    var title: String = "",
    var description: String = "",
    var author: String = "",
    var urlToImage: String = "",
    var url: String = "",
    var created_at: String = getCurrentDate()
): Serializable {

    companion object {

        private fun getCurrentDate(): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val date = Date()
            return formatter.format(date)
        }

        fun mapper(entity: BreakingNewsEntity): BreakingNewsObject {
            return BreakingNewsObject(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                author = entity.author,
                urlToImage = entity.urlToImage,
                url = entity.url
            )
        }
    }
}