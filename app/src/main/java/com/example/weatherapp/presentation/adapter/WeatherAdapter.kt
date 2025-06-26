package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.databinding.ItemClimaHoraBinding
import com.example.weatherapp.domain.model.previsao5dias.WeatherPrevisoes
import com.example.weatherapp.help.Conversor
import com.example.weatherapp.presentation.adapter.WeatherAdapter.WeatherViewHolder
import com.squareup.picasso.Picasso

class WeatherAdapter() : Adapter<WeatherViewHolder>() {
    private var listaWeather = listOf<WeatherPrevisoes>()

    fun adicionarLista(lista: List<WeatherPrevisoes>) {
        listaWeather = lista
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: ItemClimaHoraBinding) :
        ViewHolder(binding.root) {
        fun bind(clima: WeatherPrevisoes) {
            val dataFormata = Conversor.formaTempo(clima.date)
            val celsius = Conversor.formataTempKelvinCelsius(clima.temperature)
            binding.textHora.text = dataFormata
            binding.textGraus.text = celsius
            Picasso.get()
                .load(clima.iconUrl)
                .into(binding.imageHora)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClimaHoraBinding.inflate(layoutInflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val lista = listaWeather[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int {
        return listaWeather.size
    }

}