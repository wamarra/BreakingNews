package br.com.breakingnews.di

import android.content.Context
import androidx.room.Room
import br.com.breakingnews.core.utils.Constants
import br.com.breakingnews.data.database.BreakingNewsDao
import br.com.breakingnews.data.database.BreakingNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BreakingNewsDatabase = Room.databaseBuilder(
        context,
        BreakingNewsDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCoinDao(
        database: BreakingNewsDatabase
    ): BreakingNewsDao = database.getBreakingNewsDao()
}