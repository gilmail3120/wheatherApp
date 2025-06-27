package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemClimaDiasBinding
import com.example.weatherapp.domain.model.previsao5dias.WheatherPrevisoesDias
import com.example.weatherapp.help.Conversor
import com.squareup.picasso.Picasso

class DiasAdapter():Adapter<DiasAdapter.DiasViewHolder>() {
    private var listaDias = listOf<WheatherPrevisoesDias>()

    fun adicionarLista(lista:List<WheatherPrevisoesDias>){
        listaDias = lista
        notifyDataSetChanged()
    }

    inner class DiasViewHolder(private val binding: ItemClimaDiasBinding):ViewHolder(binding.root){
        fun bind(clima: WheatherPrevisoesDias){
            val tempMinFormatada = Conversor.formataTempKelvinCelsius(clima.temp_min)
            val tempMaxFormatada = Conversor.formataTempKelvinCelsius(clima.temp_max)
            val dataFormatada = Conversor.formataDataSemana(clima.date)
            with(binding){
                textCLimaDia.text=dataFormatada
                textCLimaDiaStatus.text = clima.main
                textClimaDiaMinMax.text = "$tempMinFormatada/$tempMaxFormatada"
                clima.iconUrl.let {
                    Picasso.get()
                        .load(clima.iconUrl)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.ic_broken_image)
                        .into(binding.imageCLimaDia)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiasViewHolder {
     val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClimaDiasBinding.inflate(layoutInflater,parent,false)
        return DiasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiasViewHolder, position: Int) {
       val lista =  listaDias[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int {
        return listaDias.size
    }

}