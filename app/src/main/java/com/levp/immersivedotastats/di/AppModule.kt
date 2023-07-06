package com.levp.immersivedotastats.di

import android.app.Application
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.levp.immersivedotastats.data.ApolloStratzApiClient
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroDatabase
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepositoryImpl
import com.levp.immersivedotastats.domain.network.interfaces.StratzApiClient
import com.levp.immersivedotastats.domain.usecases.GetUserInfoUseCase
import com.levp.immersivedotastats.utils.StratzApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideHeroRepository(db: HeroDatabase): HeroInfoRepository {
        return HeroInfoRepositoryImpl(db.heroInfoDao)
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
        return ApolloStratzApiClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideUserInfoUseCase(apiClient: StratzApiClient): GetUserInfoUseCase {
        return GetUserInfoUseCase(apiClient)
    }
}
