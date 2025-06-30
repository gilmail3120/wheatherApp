package com.example.weatherapp.help

import android.content.Context
import com.example.weatherapp.constantes.Constantes

object SharedPreferences {
    fun salvaPreferences(context: Context,cidade:String){
        val preference = context.getSharedPreferences(Constantes.SALVAR, Context.MODE_PRIVATE)
        preference.edit().putString(Constantes.SALVAR,cidade).apply()
    }

    fun recuperarPreferences(context: Context):String?{
        val preference = context.getSharedPreferences(Constantes.SALVAR,Context.MODE_PRIVATE)
        return preference.getString(Constantes.SALVAR,null)
    }
}