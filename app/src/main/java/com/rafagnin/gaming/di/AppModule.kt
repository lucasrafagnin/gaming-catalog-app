package com.rafagnin.gaming.di

import com.rafagnin.gaming.BuildConfig
import com.rafagnin.gaming.data.remote.GamingService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("key", BuildConfig.RAWG_API_KEY)
            .build()

        val newRequest = original
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideMoshiConverter() = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.rawg.io/")
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGameApi(retrofit: Retrofit) = retrofit.create(GamingService::class.java)
}
