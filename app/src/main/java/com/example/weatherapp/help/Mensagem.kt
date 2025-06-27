package com.example.weatherapp.help

import android.content.Context
import android.widget.Toast

object Mensagem {
    fun exibir(context: Context, mensagem:String){
        Toast.makeText(context,mensagem, Toast.LENGTH_SHORT).show()
    }
}