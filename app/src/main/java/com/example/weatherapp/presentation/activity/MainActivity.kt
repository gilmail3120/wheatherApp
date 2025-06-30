package com.example.weatherapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DetalhesActivity
import com.example.weatherapp.R
import com.example.weatherapp.constantes.Constantes
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.help.Conversor
import com.example.weatherapp.help.Mensagem
import com.example.weatherapp.help.SharedPreferences
import com.example.weatherapp.presentation.adapter.DiasAdapter
import com.example.weatherapp.presentation.adapter.HorasAdapter
import com.example.weatherapp.presentation.viewModel.WeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapterHoras: HorasAdapter
    private lateinit var adapterDias: DiasAdapter
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val weatherViewModel: WeatherViewModel by viewModels()
    private var pesquisa: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarAdapterHoras()
        iniciarAdapterDias()
        observaveis()
        searchViewPesquisa()
    }



    override fun onStart() {
        super.onStart()
        val cidade = SharedPreferences.recuperarPreferences(this)
        if (cidade!=null){
            pesquisa = cidade
            weatherViewModel.obterPrevisoesHoras(pesquisa)
            weatherViewModel.obterPrevisaoDias(pesquisa)
            weatherViewModel.obterPrevisaoAtual(pesquisa)
        }else{
            pesquisa ="nova york"
        }
    }

    private fun iniciarAdapterDias() {
        adapterDias = DiasAdapter()
        binding.recycleClimaDias.adapter = adapterDias
        binding.recycleClimaDias.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun searchViewPesquisa() {
        binding.searchCidade.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotBlank()== true) {
                    pesquisa = query.toString().trim()
                    weatherViewModel.obterPrevisaoAtual(pesquisa)
                    weatherViewModel.obterPrevisaoDias(pesquisa)
                    weatherViewModel.obterPrevisoesHoras(pesquisa)
                    SharedPreferences.salvaPreferences(this@MainActivity,pesquisa)
                    binding.searchCidade.clearFocus()
                    binding.searchCidade.setQuery("",false)
                } else {
                    Toast.makeText(this@MainActivity,"Por favor preencha o campo de pesquisa",Toast.LENGTH_SHORT).show()
                }

                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    private fun observaveis() {
        weatherViewModel.mensagemErro.observe(this) { erro->
            Mensagem.exibir(this,erro)
        }
        weatherViewModel.listaClimaHoras.observe(this) { listaHoras ->
            listaHoras?.let {
                adapterHoras.adicionarLista(listaHoras)
            }
        }
        weatherViewModel.listaClimaDias.observe(this) { listaDias ->
            listaDias?.let {
                adapterDias.adicionarLista(listaDias)
            }
        }
        weatherViewModel.listaAtual.observe(this) { atual ->
            binding.cardClimaHoje.setOnClickListener {
                val intent = Intent(this, DetalhesActivity::class.java)
                intent.putExtra(Constantes.DADOSATUAL,atual)
                startActivity(intent)
            }
            val dataFormatada = Conversor.formataDataMes(atual.dt)
            with(binding) {
                textHojeData.text = dataFormatada
                textClimaStatus.text = atual.description
                textMinimaMaxima.text = Conversor.formataTempKelvinCelsius(atual.temp)
                textClimaUmidade.text = "${atual.humidity} %"
                textClimaUv.text = "${atual.wind.speed}m/s"
                textLocalCLima.text = atual.name.replaceFirstChar { it.uppercase() }
                if (atual.icon.isNotEmpty()) {
                    Picasso.get()
                        .load(atual.icon)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.ic_broken_image)
                        .into(imageClimaHoje)
                }
            }
        }
    }

    private fun iniciarAdapterHoras() {
        adapterHoras = HorasAdapter()
        with(binding) {
            recyclerClimaHora.adapter = adapterHoras
            recyclerClimaHora.layoutManager =
                LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        }
    }
}