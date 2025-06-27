package com.example.weatherapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.repository.IWeatherRepository
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoesHoras
import com.example.weatherapp.domain.model.previsao5dias.WheatherPrevisoesDias
import com.example.weatherapp.domain.model.previsaoagora.PrevisaoAtual
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val weatherRepositoryImpl: IWeatherRepository) :
    ViewModel() {
    private val _listaClimaHoras = MutableLiveData<List<WeatherPrevisoesHoras>?>()
    val listaClimaHoras: LiveData<List<WeatherPrevisoesHoras>?>
        get() = _listaClimaHoras

    private val _listaClimaDias = MutableLiveData<List<WheatherPrevisoesDias>?>()
    val listaClimaDias: LiveData<List<WheatherPrevisoesDias>?>
        get() = _listaClimaDias

    private val _listaAtual = MutableLiveData<PrevisaoAtual>()
    val listaAtual: LiveData<PrevisaoAtual>
        get() = _listaAtual

    fun obterPrevisoesHoras(cidade: String) {
        viewModelScope.launch {
            val lista = weatherRepositoryImpl.obterPrevisaoHoras(cidade)
            Log.i("resposta", "viewModel:$lista ")
            _listaClimaHoras.postValue(lista)
        }
    }

    fun obterPrevisaoDias(cidade:String){
        viewModelScope.launch {
            val lista = weatherRepositoryImpl.obterPrevisaoDias(cidade)
            _listaClimaDias.postValue(lista)
        }
    }
    fun obterPrevisaoAtual(cidade:String){
        viewModelScope.launch {
            val lista = weatherRepositoryImpl.obterPrevisaoAtual(cidade)
            _listaAtual.postValue(lista)
        }
    }
}