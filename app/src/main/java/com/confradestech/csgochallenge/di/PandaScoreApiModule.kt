package com.confradestech.csgochallenge.di

import android.app.Application
import com.confradestech.csgochallenge.BuildConfig.BASE_URL
import com.confradestech.csgochallenge.BuildConfig.TOKEN
import com.confradestech.csgochallenge.domain.repository.remote.PandaScoreEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.Arrays
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object PandaScoreApiModule {

    @Provides
    @Singleton
    fun providePandaScoreApi(context: Application): PandaScoreEndpoint {

        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
                init(null as KeyStore?)
            }
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers = trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            "Unexpected default trust managers:" + Arrays.toString(
                trustManagers
            )
        }
        val trustManager = trustManagers[0] as X509TrustManager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
        val clientBuilder = OkHttpClient.Builder()
        setClientBuilder(clientBuilder)
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        clientBuilder.readTimeout(60, TimeUnit.SECONDS)
        clientBuilder.followRedirects(false)
        clientBuilder.followSslRedirects(false)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
            .create(PandaScoreEndpoint::class.java)
    }

    private fun setClientBuilder(clientBuilder: OkHttpClient.Builder) {
        val interceptor = Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            //Response
            request
                .newBuilder()
                .addHeader("Authorization", "Bearer $TOKEN")
                .addHeader("accept", "application/json")
            val response = chain.proceed(request)
            val responseBody = response.body()
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE)
            response
        }
        clientBuilder.addInterceptor(interceptor)
    }

}