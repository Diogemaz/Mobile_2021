package com.diogenes.shortbio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.*

class experienciaActivity : AppCompatActivity() {
    lateinit var formacao: TextView
    lateinit var sobre: TextView
    lateinit var objetivo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiencia)

        formacao = findViewById(R.id.formacao)
        formacao.setOnClickListener(){
            val intent = Intent(this@experienciaActivity, formacaoActivity::class.java)
            startActivity(intent)
        }


        sobre = findViewById(R.id.experiencia)
        sobre.setOnClickListener(){
            val intent = Intent(this@experienciaActivity, MainActivity::class.java)
            startActivity(intent)
        }


        objetivo = findViewById(R.id.objetivo)
        objetivo.setOnClickListener(){
            val intent = Intent(this@experienciaActivity, objetivoActivity::class.java)
            startActivity(intent)
        }
    }
}