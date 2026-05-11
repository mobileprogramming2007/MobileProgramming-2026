package com.example.mobileprogramminglabs.model.di

import com.example.mobileprogramminglabs.model.datasource.network.service.HabitApiService
import com.example.mobileprogramminglabs.model.repository.habit.HabitRepository
import com.example.mobileprogramminglabs.model.repository.habit.HabitRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://172.20.10.2:8000/" //10.20.3.170
    //http://192.168.100.6:8000/
    // for emulator: http://10.0.2.2:8000/

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHabitApiService(
        retrofit: Retrofit
    ): HabitApiService {
        return retrofit.create(HabitApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHabitRepository(
        apiService: HabitApiService
    ): HabitRepository {
        return HabitRepositoryImpl(apiService)
    }
}