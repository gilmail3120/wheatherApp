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

    private val _mensagemErro = MutableLiveData<String>()
    val mensagemErro: LiveData<String>
        get() = _mensagemErro

    private val _listaAtual = MutableLiveData<PrevisaoAtual>()
    val listaAtual: LiveData<PrevisaoAtual>
        get() = _listaAtual

    fun obterPrevisoesHoras(cidade: String) {
        viewModelScope.launch {
            try {
                val lista = weatherRepositoryImpl.obterPrevisaoHoras(cidade)
                if (lista.isEmpty()) {
                    _mensagemErro.postValue("Não encontrato.")
                } else {
                    _listaClimaHoras.postValue(lista)
                }
            } catch (erro: Exception) {
                erro.printStackTrace()
                _mensagemErro.postValue("Erro ao carregar previsões de horas $erro")
            }
        }
    }

    fun obterPrevisaoDias(cidade: String) {
        viewModelScope.launch {
            try {
                val lista = weatherRepositoryImpl.obterPrevisaoDias(cidade)
                if (lista.isEmpty()){
                    _mensagemErro.postValue("Não encontrado")
                }else{
                    _listaClimaDias.postValue(lista)
                }
            } catch (erro: Exception) {
                erro.printStackTrace()
                _mensagemErro.postValue("Erro ao carregar previsões de dias.$erro")
            }
        }
    }

    fun obterPrevisaoAtual(cidade: String) {
        viewModelScope.launch {
            try {
                val lista = weatherRepositoryImpl.obterPrevisaoAtual(cidade)
                if ( lista== null){
                    _mensagemErro.postValue("Não encontrado")
                }else{
                    _listaAtual.postValue(lista)
                }
            }catch(erro: Exception){
                erro.printStackTrace()
                _mensagemErro.postValue("Erro ao carregar previsões de atuais.$erro")
            }
        }
    }
}