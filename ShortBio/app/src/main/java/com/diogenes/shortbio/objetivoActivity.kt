package com.diogenes.shortbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class objetivoActivity : AppCompatActivity() {
    lateinit var formacao: TextView
    lateinit var experiencia: TextView
    lateinit var sobre: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)

        formacao = findViewById(R.id.formacao)
        formacao.setOnClickListener(){
            val intent = Intent(this@objetivoActivity, formacaoActivity::class.java)
            startActivity(intent)
        }


        experiencia = findViewById(R.id.experiencia)
        experiencia.setOnClickListener(){
            val intent = Intent(this@objetivoActivity, experienciaActivity::class.java)
            startActivity(intent)
        }


        sobre = findViewById(R.id.sobre)
        sobre.setOnClickListener(){
            val intent = Intent(this@objetivoActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}