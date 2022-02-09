package br.com.breakingnews.data

import br.com.breakingnews.core.utils.ResponseWrapper
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import kotlinx.coroutines.flow.Flow

interface BreakingNewsRepository {

    suspend fun getBreakingNews(): ResponseWrapper<List<BreakingNewsEntity>>

    suspend fun insertBreakingNews(breankinNews: List<BreakingNewsEntity>)

    fun getBreakingNewsFromDb(): Flow<List<BreakingNewsEntity>>

    suspend fun deleteAll()
}