package br.com.breakingnews.domain

import br.com.breakingnews.core.utils.ResponseWrapper
import br.com.breakingnews.data.BreakingNewsRepository
import br.com.breakingnews.data.database.BreakingNewsDao
import br.com.breakingnews.data.database.models.BreakingNewsObject
import br.com.breakingnews.data.network.ApiService
import br.com.breakingnews.data.network.utils.RetrofitWrapper
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BreakingNewsRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val breakingNewsDao: BreakingNewsDao,
    private val retrofitWrapper: RetrofitWrapper
) : BreakingNewsRepository {

    override suspend fun getBreakingNews(): ResponseWrapper<List<BreakingNewsEntity>> {
        val response = retrofitWrapper.request {
            service.getBreakingNews()
        }

        return when(response) {
            is ResponseWrapper.Success -> {
                val entities = BreakingNewsEntity.mapper(response.result.articles)

                insertBreakingNews(breankinNews = entities)

                ResponseWrapper.Success(result = entities)
            }
            is ResponseWrapper.Error -> {
                response
            }
        }
    }

    override suspend fun insertBreakingNews(breankinNews: List<BreakingNewsEntity>) {
        breankinNews.forEach { entity ->
            val breakingNewsObject = BreakingNewsObject.mapper(entity)

            breakingNewsDao.insertBreakingNews(breakingNewsObject)
        }
    }

    override fun getBreakingNewsFromDb(): Flow<List<BreakingNewsEntity>> {
        return breakingNewsDao.getAllBreakingNews().map {
            BreakingNewsEntity.mapper(it)
        }
    }

    override suspend fun deleteAll() {
        breakingNewsDao.deleteAll()
    }
}