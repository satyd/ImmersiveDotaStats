package com.levp.immersivedotastats.di

import android.app.Application
import androidx.room.Room
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroDatabase
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepository
import com.levp.immersivedotastats.domain.database.heroesinfo.HeroInfoRepositoryImpl
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
}