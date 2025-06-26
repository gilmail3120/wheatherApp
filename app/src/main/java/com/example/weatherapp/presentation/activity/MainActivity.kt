package com.example.weatherapp.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.adapter.WeatherAdapter
import com.example.weatherapp.presentation.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapterWeather: WeatherAdapter
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val weatherViewModel: WeatherViewModel by viewModels()
    private var pesquisa: String = "nova york"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarAdapter()
        observaveis()
        searchViewPesquisa()
    }

    private fun searchViewPesquisa() {
        binding.searchCidade.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                pesquisa = query.toString()
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {

                return true
            }
        })
    }

    private fun observaveis() {
        weatherViewModel.listaClima.observe(this) { lista ->
           lista?.let {
               adapterWeather.adicionarLista(lista)
               Log.i("resposta", "observaveis:$lista ")
           }
        }
    }

    override fun onStart() {
        super.onStart()
        weatherViewModel.obterPrevisoes(pesquisa)
    }

    private fun iniciarAdapter() {
        adapterWeather = WeatherAdapter()
        with(binding) {
            recyclerClimaHora.adapter = adapterWeather
            recyclerClimaHora.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        }
    }
}