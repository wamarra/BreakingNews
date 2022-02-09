package br.com.breakingnews.data.network.models

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String,
    val url: String
)
