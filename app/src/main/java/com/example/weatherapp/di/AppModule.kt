package com.example.weatherapp.di

import com.example.weatherapp.constantes.Constantes
import com.example.weatherapp.data.remote.api.IWeatherAPI
import com.example.weatherapp.data.remote.repository.IWeatherRepository
import com.example.weatherapp.data.remote.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.model.ApiKey
import com.example.weatherapp.domain.model.Linguagem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiKey(): ApiKey = ApiKey(Constantes.APIKEY)

    @Provides
    fun provideLinguagem(): Linguagem = Linguagem(Constantes.LINGUAGEM)

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideWeatherAPI(retrofit: Retrofit): IWeatherAPI{
        return retrofit.create(IWeatherAPI::class.java)
    }

    @Provides
    fun provideWeatherRepository(iWeatherAPI: IWeatherAPI, apiKey:ApiKey,linguagem: Linguagem): IWeatherRepository{
        return WeatherRepositoryImpl(iWeatherAPI,apiKey,linguagem)
    }



}