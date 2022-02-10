package br.com.breakingnews.domain.entities

import br.com.breakingnews.data.database.models.BreakingNewsObject
import br.com.breakingnews.data.network.models.Article

data class BreakingNewsEntity(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val urlToImage: String,
    val url: String
) {

    companion object {

        fun mapper(data: List<Article>): List<BreakingNewsEntity> {
            return data.map { article ->
                println(article.author)
                BreakingNewsEntity(
                    id = article.title.hashCode().toString(),
                    title = article.title,
                    description = article.description,
                    author = article.author ?: "Autor desconhecido",
                    urlToImage = article.urlToImage,
                    url = article.url
                )
            }
        }

        @JvmName("mapper_from_object")
        fun mapper(objectsData: List<BreakingNewsObject>): List<BreakingNewsEntity> {
            return objectsData.map { obj ->
                BreakingNewsEntity(
                    id = obj.id,
                    title = obj.title,
                    description = obj.description,
                    author = obj.author,
                    urlToImage = obj.urlToImage,
                    url = obj.url
                )
            }
        }
    }
}