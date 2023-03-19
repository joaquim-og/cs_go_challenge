package com.confradestech.csgochallenge.di

import com.confradestech.csgochallenge.domain.repository.PandaScorePointRepository
import com.confradestech.csgochallenge.domain.repository.mappers.MatchesItemMapper
import com.confradestech.csgochallenge.domain.repository.mappers.PlayersItemMapper
import com.confradestech.csgochallenge.domain.usecases.GetPlayerListUseCase
import com.confradestech.csgochallenge.domain.usecases.RunningMatchesUseCase
import com.confradestech.csgochallenge.domain.usecases.UpcomingMatchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //region UseCases
    @Provides
    @Singleton
    fun runningMatchesUseCase(repository: PandaScorePointRepository): RunningMatchesUseCase =
        RunningMatchesUseCase(repository)

    @Provides
    @Singleton
    fun upcomingMatchesUseCase(repository: PandaScorePointRepository): UpcomingMatchesUseCase =
        UpcomingMatchesUseCase(repository)

    @Provides
    @Singleton
    fun getPlayerListUseCase(repository: PandaScorePointRepository): GetPlayerListUseCase =
        GetPlayerListUseCase(repository)
    //endregion UseCases

    //region Mappers
    @Provides
    @Singleton
    fun matchesItemMapper(): MatchesItemMapper =
        MatchesItemMapper()

    @Provides
    @Singleton
    fun playersItemMapper(): PlayersItemMapper =
        PlayersItemMapper()
    //endregion Mappers

}