package com.odin.pagingsample.di

import com.odin.pagingsample.BuildConfig
import com.odin.pagingsample.network.KakaoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    const val BASE_URL = "https://dapi.kakao.com/"
    private const val header = ""

    @Provides
    fun provideKakaoService(): KakaoService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(AppInterceptor(),provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(KakaoService::class.java)
    }

    private fun provideOkHttpClient(
        interceptor: AppInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


       return OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                addNetworkInterceptor(httpLoggingInterceptor)
                build()
            }
    }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", header)
                .build()
            proceed(newRequest)
        }
    }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}