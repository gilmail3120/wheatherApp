package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.databinding.ItemClimaDiasBinding
import com.example.weatherapp.presentation.adapter.WeatherAdapter.WeatherViewHolder

class WeatherAdapter() : Adapter<WeatherViewHolder>() {
    private var listaWeather = listOf<String>()

    fun adicionarLista(lista: List<String>) {
        listaWeather = lista
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: ItemClimaDiasBinding) :
        ViewHolder(binding.root) {
        fun bind(clima: String) {


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClimaDiasBinding.inflate(layoutInflater, parent, false)
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