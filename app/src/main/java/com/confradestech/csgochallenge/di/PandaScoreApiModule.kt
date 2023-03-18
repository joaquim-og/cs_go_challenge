package com.confradestech.csgochallenge.di

import android.app.Application
import com.confradestech.csgochallenge.BuildConfig.BASE_URL
import com.confradestech.csgochallenge.domain.remote.PandaScoreEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PandaScoreApiModule {

    @Provides
    @Singleton
    fun providePandaScoreApi(context: Application): PandaScoreEndpoint {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()
            .create(PandaScoreEndpoint::class.java)
    }

}