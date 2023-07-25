package com.levp.immersivedotastats.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.levp.immersivedotastats.data.repository.ApolloStratzApiClientImpl
import com.levp.immersivedotastats.data.database.heroesinfo.HeroDatabase
import com.levp.immersivedotastats.domain.repository.StatsRepository
import com.levp.immersivedotastats.data.repository.StatsRepositoryImpl
import com.levp.immersivedotastats.data.remote.interfaces.StratzApiClient
import com.levp.immersivedotastats.domain.use_case.GetUserHeroesPerformanceUseCase
import com.levp.immersivedotastats.domain.use_case.GetUserInfoUseCase
import com.levp.immersivedotastats.domain.use_case.GetUserRecentMatchesUseCase
import com.levp.immersivedotastats.domain.use_case.LoadImageUseCase
import com.levp.immersivedotastats.utils.StratzApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): HeroDatabase {
        return Room.databaseBuilder(
            app,
            HeroDatabase::class.java,
            "heroes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeroRepository(db: HeroDatabase): StatsRepository {
        return StatsRepositoryImpl(db.heroInfoDao)
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://api.stratz.com/graphql")
            .addHttpHeader("Authorization", StratzApiKey.API_KEY)
            .build()
    }

    @Provides
    @Singleton
    fun provideStratzApiClient(apolloClient: ApolloClient): StratzApiClient {
        return ApolloStratzApiClientImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideUserInfoUseCase(apiClient: StratzApiClient): GetUserInfoUseCase {
        return GetUserInfoUseCase(apiClient)
    }

    @Provides
    @Singleton
    fun provideRecentMatchesUseCase(apiClient: StratzApiClient): GetUserRecentMatchesUseCase {
        return GetUserRecentMatchesUseCase(apiClient)
    }

    @Provides
    @Singleton
    fun provideGetHeroesPerformanceStatUseCase(apiClient: StratzApiClient): GetUserHeroesPerformanceUseCase {
        return GetUserHeroesPerformanceUseCase(apiClient)
    }

    @Provides
    @Singleton
    fun provideLoadImageUseCase(
        @ApplicationContext appContext: Context
    ): LoadImageUseCase {
        return LoadImageUseCase(appContext)
    }
}
