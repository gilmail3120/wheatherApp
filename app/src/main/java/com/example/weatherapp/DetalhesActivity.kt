package com.example.weatherapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.constantes.Constantes
import com.example.weatherapp.databinding.ActivityDetalhesBinding
import com.example.weatherapp.domain.model.previsaoagora.PrevisaoAtual
import com.example.weatherapp.help.Conversor
import com.example.weatherapp.help.Mensagem
import com.example.weatherapp.presentation.activity.MainActivity
import com.example.weatherapp.presentation.viewModel.WeatherViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DetalhesActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetalhesBinding.inflate(layoutInflater) }
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getBundle()
        observaveis()
        eventoClique()

    }

    private fun eventoClique() {
        binding.imageBtnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun observaveis() {
        weatherViewModel.listaAtual.observe(this) { atual ->
            val sunrise = Conversor.formaTempo(atual.sunrise)
            val sunset = Conversor.formaTempo(atual.sunset)
            val tempMin = Conversor.formataTempKelvinCelsius(atual.temp_min)
            val tempMax = Conversor.formataTempKelvinCelsius(atual.temp_max)
                with(binding) {
                    textDetalhesLocal.text = atual.name
                    textDetalheMinxMaxCard.text = "${tempMin}/${tempMax}"
                    textDetalhesTemp.text = Conversor.formataTempKelvinCelsiusSemFormatacao(atual.temp)
                    textDetalheVento.text = "${atual.wind.speed}m/s"
                    textDetalheUmidade.text = "${atual.humidity}%"
                    textDetalhePressao.text = "${atual.pressure} hPa"
                    textDetalheVisibilidade.text = Conversor.formatarMetrosKm(atual.visibility)
                    textDetalheNasPor.text = "${sunrise}/${sunset}"
                    if (atual.icon != null) {
                        Picasso.get()
                            .load(atual.icon)
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.ic_broken_image)
                            .into(binding.imageDetalhe)
                    }
                }

        }
    }

    private fun getBundle() {
        val bundle = intent.extras
        if (bundle != null) {
            val dadosAtual = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(Constantes.DADOSATUAL, PrevisaoAtual::class.java)
            } else {
                bundle.getParcelable(Constantes.DADOSATUAL)
            }
            dadosAtual?.let {
                weatherViewModel.obterPrevisaoAtual(dadosAtual.name)
            } ?: "Nao carregado"
        } else {
            Mensagem.exibir(this, "Erro ao carregar dados")
        }

    }
}