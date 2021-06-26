package com.diogenes.shortbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class formacaoActivity : AppCompatActivity() {
    lateinit var sobre: TextView
    lateinit var experiencia: TextView
    lateinit var objetivo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formacao)

        sobre = findViewById(R.id.sobre)
        sobre.setOnClickListener(){
            val intent = Intent(this@formacaoActivity, MainActivity::class.java)
            startActivity(intent)
        }


        experiencia = findViewById(R.id.experiencia)
        experiencia.setOnClickListener(){
            val intent = Intent(this@formacaoActivity, experienciaActivity::class.java)
            startActivity(intent)
        }


        objetivo = findViewById(R.id.objetivo)
        objetivo.setOnClickListener(){
            val intent = Intent(this@formacaoActivity, objetivoActivity::class.java)
            startActivity(intent)
        }
    }
}