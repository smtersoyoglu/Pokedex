package com.smtersoyoglu.pokedex.di

import com.smtersoyoglu.pokedex.common.Constants.BASE_URL
import com.smtersoyoglu.pokedex.data.remote.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun providePokeApi(retrofit: Retrofit) : PokeApi {
        return retrofit.create(PokeApi::class.java)
    }

}