package com.diogenes.shortbio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.*

class MainActivity : AppCompatActivity() {
    lateinit var formacao: TextView
    lateinit var experiencia: TextView
    lateinit var objetivo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formacao = findViewById(R.id.formacao)
        formacao.setOnClickListener(){
            val intent = Intent(this@MainActivity, formacaoActivity::class.java)
            startActivity(intent)
        }


        experiencia = findViewById(R.id.experiencia)
        experiencia.setOnClickListener(){
            val intent = Intent(this@MainActivity, experienciaActivity::class.java)
            startActivity(intent)
        }


        objetivo = findViewById(R.id.objetivo)
        objetivo.setOnClickListener(){
            val intent = Intent(this@MainActivity, objetivoActivity::class.java)
            startActivity(intent)
        }
    }
}