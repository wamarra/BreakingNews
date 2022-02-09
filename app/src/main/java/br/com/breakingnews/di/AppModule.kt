package br.com.breakingnews.di

import androidx.recyclerview.widget.DiffUtil
import br.com.breakingnews.BuildConfig
import br.com.breakingnews.core.utils.Constants
import br.com.breakingnews.data.BreakingNewsRepository
import br.com.breakingnews.data.database.BreakingNewsDao
import br.com.breakingnews.data.network.ApiService
import br.com.breakingnews.data.network.utils.RetrofitWrapper
import br.com.breakingnews.data.network.utils.RetrofitWrapperImpl
import br.com.breakingnews.domain.BreakingNewsRepositoryImpl
import br.com.breakingnews.domain.entities.BreakingNewsEntity
import br.com.breakingnews.presentation.main.MainAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitWrapper(): RetrofitWrapper = RetrofitWrapperImpl()

    @Provides
    @Singleton
    fun provideRepository(
        service: ApiService,
        dao: BreakingNewsDao,
        retrofitWrapper: RetrofitWrapper
    ): BreakingNewsRepository = BreakingNewsRepositoryImpl(service, dao, retrofitWrapper)

    @Provides
    @Singleton
    fun provideDiffUtilCallback() : DiffUtil.ItemCallback<BreakingNewsEntity> = object : DiffUtil.ItemCallback<BreakingNewsEntity>() {
        override fun areItemsTheSame(
            oldItem: BreakingNewsEntity,
            newItem: BreakingNewsEntity
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: BreakingNewsEntity,
            newItem: BreakingNewsEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    @Provides
    @Singleton
    fun provideMainAdapter(
        diffUtil: DiffUtil.ItemCallback<BreakingNewsEntity>
    ): MainAdapter = MainAdapter(diffUtil)
}