package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.databinding.ItemClimaHoraBinding
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoesHoras
import com.example.weatherapp.help.Conversor
import com.example.weatherapp.presentation.adapter.HorasAdapter.WeatherViewHolder
import com.squareup.picasso.Picasso

class HorasAdapter() : Adapter<WeatherViewHolder>() {
    private var listaHoras = listOf<WeatherPrevisoesHoras>()

    fun adicionarLista(lista: List<WeatherPrevisoesHoras>) {
        listaHoras = lista
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: ItemClimaHoraBinding) :
        ViewHolder(binding.root) {
        fun bind(clima: WeatherPrevisoesHoras) {
            val dataFormata = Conversor.formaTempo(clima.date)
            val celsius = Conversor.formataTempKelvinCelsius(clima.temperature)
            with(binding){
                textHora.text = dataFormata
                textGraus.text = celsius
                clima.iconUrl.let {
                    Picasso.get()
                        .load(clima.iconUrl)
                        .into(imageHora)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClimaHoraBinding.inflate(layoutInflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val lista = listaHoras[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int {
        return listaHoras.size
    }

}