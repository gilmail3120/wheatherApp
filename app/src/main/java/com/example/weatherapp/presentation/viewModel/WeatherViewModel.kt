package com.example.weatherapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.repository.IWeatherRepository
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val weatherRepositoryImpl: IWeatherRepository) :
    ViewModel() {
    private val _listaClima = MutableLiveData<List<WeatherPrevisoes>?>()
    val listaClima: LiveData<List<WeatherPrevisoes>?>
        get() = _listaClima

    fun obterPrevisoes(cidade: String) {

            viewModelScope.launch {
                val lista = weatherRepositoryImpl.obterPrevisao5Dias(cidade)
                Log.i("resposta", "viewModel:$lista ")
                _listaClima.postValue(lista)
            }
    }
}