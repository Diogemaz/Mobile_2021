package com.diogenes.listadetarefacomretrofit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diogenes.listadetarefacomretrofit.data.model.Tarefa
import com.diogenes.listadetarefacomretrofit.util.Network
import com.diogenes.listadetarefacomretrofit.domain.TarefaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), TarefaAdapterListener {

    lateinit var tarefaAdapter: TarefaAdapter
    lateinit var edtTarefa: EditText
    lateinit var btnIncluir: ImageButton
    lateinit var preferenciasTarefa: SharedPreferences
    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // #Preferencias
        // Criando o arquivo de preferĂȘncias
        preferenciasTarefa = getSharedPreferences("tarefaPreferences", Context.MODE_PRIVATE)

        rv = findViewById<RecyclerView>(R.id.rvTarefas)

        btnIncluir = findViewById(R.id.btnIncluir)
        edtTarefa = findViewById(R.id.edtTarefa)

        btnIncluir.setOnClickListener() {
            if (edtTarefa.text.toString().isNotEmpty()) {
                adicionarTarefa(edtTarefa.text.toString())
                edtTarefa.text.clear()

                // Apagar a preferĂȘncia gravada anteriormente
                val editor = preferenciasTarefa.edit()
                editor.remove("NOME")
                editor.commit()
            } else {
                edtTarefa.error = "Insira um texto vĂĄlido!"
            }
        }
    }

    fun adicionarTarefa(nomeTarefa: String) {
        val retrofitClient = Network.retrofitConfig("https://backend-tarefa.herokuapp.com/")
        val servico = retrofitClient.create(TarefaService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                servico.adicionarTarefa(Tarefa(nome=nomeTarefa))
                val response = servico.buscarTarefas()

                withContext(Main){
                    if (response.isSuccessful){
                        response.body()?.let{
                            tarefaAdapter.refreshListTarefa(it)
                        }
                        Toast.makeText(this@MainActivity, "Tarefa adicionada", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception){
                Log.e("ADD_TAREFA", e.toString(), e)
                withContext(Main){
                    Toast.makeText(this@MainActivity, "NĂŁo foi possĂ­vel processar a sua solicitaĂ§ĂŁo!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun recuperarLista(){
        val retrofitClient = Network.retrofitConfig("https://backend-tarefa.herokuapp.com/")
        val servico = retrofitClient.create(TarefaService::class.java)

        // Coroutine para Entrada/SaĂ­da de Dados
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = servico.buscarTarefas()

                withContext(Main) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            tarefaAdapter = TarefaAdapter(it, this@MainActivity)

                            // Vincula o Adapter na Recycler View
                            rv.adapter = tarefaAdapter

                            // Exibe os itens em uma coluna Ășnica no padrĂŁo vertical
                            rv.layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                }
            } catch (e: Exception){
                Log.e("SELECT_TAREFA", e.toString(), e)
                withContext(Main){
                    Toast.makeText(this@MainActivity, "NĂŁo foi possĂ­vel processar a sua solicitaĂ§ĂŁo!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // #Preferencias
    override fun onPause() {
        super.onPause()

        if (edtTarefa.text.toString().isNotEmpty()){

            // Cria e ediĂ§ĂŁo na preferĂȘncia
            val editor = preferenciasTarefa.edit()

            // Escreve um uma preferĂȘncia
            editor.putString("NOME", edtTarefa.text.toString())

            // Salva a preferĂȘncia
            editor.commit()
        }
    }

    // #Preferencias
    override fun onResume() {
        super.onResume()

        // Recupera (lĂȘ) uma preferĂȘncia e utiliza ela populando um Edit Text
        edtTarefa.setText(preferenciasTarefa.getString("NOME", null))
    }

    override fun onStart() {
        super.onStart()

        recuperarLista()
    }

    override fun excluirTarefa(tarefa: Tarefa) {
        val retrofitClient = Network.retrofitConfig("https://backend-tarefa.herokuapp.com/")
        val servico = retrofitClient.create(TarefaService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                servico.excluirTarefa(tarefa.id)
                val response = servico.buscarTarefas()

                withContext(Main){
                    if (response.isSuccessful){
                        response.body()?.let{
                            tarefaAdapter.refreshListTarefa(it)
                        }
                        Toast.makeText(this@MainActivity, "Tarefa excluĂ­da", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception){
                Log.e("DELETE_TAREFA", e.toString(), e)
                withContext(Main){
                    Toast.makeText(this@MainActivity, "NĂŁo foi possĂ­vel processar a sua solicitaĂ§ĂŁo!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

