package com.confradestech.csgochallenge.di

import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepository
import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPandaScoreRepository(
        pandaScorePointRepositoryImpl: PandaScorePointRepositoryImpl
    ): PandaScorePointRepository

}