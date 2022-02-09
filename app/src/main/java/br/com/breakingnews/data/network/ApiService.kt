package br.com.breakingnews.data.network

import br.com.breakingnews.core.utils.Constants
import br.com.breakingnews.data.network.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=br")
    suspend fun getBreakingNews(
        @Query("category") category: String = "technology",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<ApiResponse>
}