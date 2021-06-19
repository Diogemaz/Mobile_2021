package com.diogenes.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var resultado: TextView
    lateinit var botaoCalcular: Button
    var precoAlcool: Double? = null
    var precoGasolina: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultado = findViewById(R.id.resposta)
        botaoCalcular = findViewById(R.id.calcular)
        botaoCalcular.setOnClickListener{
            precoAlcool = findViewById<EditText>(R.id.alcool).text.toString().toDoubleOrNull()
            precoGasolina = findViewById<EditText>(R.id.gasolina).text.toString().toDoubleOrNull()
            calcularMelhor(precoAlcool, precoGasolina)
        }
    }

    fun calcularMelhor(precoAlcool: Double?, precoGasolina: Double?){
        var calculo: Double? = null
       if( precoGasolina != null){
            if(precoAlcool != null){
                calculo = precoAlcool / precoGasolina
            }
        }
        calculo?.let {
            if (calculo > 0.7) {
                resultado.text = "Melhor ultilizar gasolina"
            } else {
                resultado.text = "Melhor ultilizar alcool"
            }
        }
    }
}