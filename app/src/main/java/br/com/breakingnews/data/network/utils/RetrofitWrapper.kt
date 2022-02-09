package br.com.breakingnews.data.network.utils

import br.com.breakingnews.core.utils.ResponseWrapper
import retrofit2.Response

interface RetrofitWrapper {

    suspend fun <T> request(
        call: suspend () -> Response<T>
    ): ResponseWrapper<T>
}