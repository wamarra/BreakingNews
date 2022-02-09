package br.com.breakingnews.data.network.models

data class ApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
