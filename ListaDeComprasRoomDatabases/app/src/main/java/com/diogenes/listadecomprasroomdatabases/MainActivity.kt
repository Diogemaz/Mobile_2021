package com.diogenes.listadecomprasroomdatabases

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), ComprasAdapterListener {

    lateinit var ComprasAdapter: ComprasAdapter
    lateinit var edtCompras: EditText
    lateinit var btnIncluir: ImageButton
    lateinit var preferenciasCompras: SharedPreferences
    lateinit var rv: RecyclerView

    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // #Preferencias
        // Criando o arquivo de preferências
        preferenciasCompras = getSharedPreferences("ComprasPreferences", Context.MODE_PRIVATE)

        rv = findViewById<RecyclerView>(R.id.rvCompras)

        btnIncluir = findViewById(R.id.btnIncluir)
        edtCompras = findViewById(R.id.edtCompras)

        btnIncluir.setOnClickListener() {
            if (edtCompras.text.toString().isNotEmpty()) {
                adicionarCompras(edtCompras.text.toString())
                edtCompras.text.clear()

                // Apagar a preferência gravada anteriormente
                val editor = preferenciasCompras.edit()
                editor.remove("NOME")
                editor.commit()
            } else {
                edtCompras.error = "Insira um texto válido!"
            }
        }
    }

    fun adicionarCompras(nomeCompras: String) {
        // Coroutine para Entrada/Saída de Dados
        CoroutineScope(Dispatchers.IO).launch {
            // Cria ou recupera o BD da aplicação
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            // A partir do DAO executa uma ação escolhida (INSERT)
            db?.ComprasDao()?.addCompras(Compras(nome = nomeCompras))

            // Recupera ações/métodos de acesso a dados da entidade (tabela)
            val ComprasDAO = db?.ComprasDao()

            // A partir do DAO executa uma ação escolhida (SELECT)
            val resposta = ComprasDAO?.getCompras()

            // Coroutine para UI
            withContext(Dispatchers.Main) {
                resposta?.let {
                    ComprasAdapter.refreshListCompras(resposta)
                }
            }
        }
    }

    fun recuperarLista(){
        // Coroutine para Entrada/Saída de Dados
        CoroutineScope(Dispatchers.IO).launch {
            // Cria ou recupera o BD da aplicação
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            // Recupera ações/métodos de acesso a dados da entidade (tabela)
            val ComprasDAO = db?.ComprasDao()

            // A partir do DAO executa uma ação escolhida (SELECT)
            val resposta = ComprasDAO?.getCompras()

            // Coroutine para UI
            withContext(Dispatchers.Main){
                resposta?.let{
                    ComprasAdapter = ComprasAdapter(it, this@MainActivity)

                    // Vincula o Adapter na Recycler View
                    rv.adapter = ComprasAdapter

                    // Exibe os itens em uma coluna única no padrão vertical
                    rv.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }
    }

    // #Preferencias
    override fun onPause() {
        super.onPause()

        if (edtCompras.text.toString().isNotEmpty()){

            // Cria e edição na preferência
            val editor = preferenciasCompras.edit()

            // Escreve um uma preferência
            editor.putString("NOME", edtCompras.text.toString())

            // Salva a preferência
            editor.commit()
        }
    }

    // #Preferencias
    override fun onResume() {
        super.onResume()

        // Recupera (lê) uma preferência e utiliza ela populando um Edit Text
        edtCompras.setText(preferenciasCompras.getString("NOME", null))
    }

    override fun onStart() {
        super.onStart()

        recuperarLista()
    }

    override fun excluirCompras(Compras: Compras) {
        // Coroutine para Entrada/Saída de Dados
        CoroutineScope(Dispatchers.IO).launch {
            // Cria ou recupera o BD da aplicação
            db = DatabaseBuilder.getAppDatabase(this@MainActivity)

            // A partir do DAO executa uma ação escolhida (DELETE)
            db?.ComprasDao()?.deleteCompras(Compras)

            // Recupera ações/métodos de acesso a dados da entidade (tabela)
            val tarefaDAO = db?.ComprasDao()

            // A partir do DAO executa uma ação escolhida (SELECT)
            val resposta = tarefaDAO?.getCompras()

            // Coroutine para UI
            withContext(Dispatchers.Main) {
                resposta?.let {
                    ComprasAdapter.refreshListCompras(resposta)

                    Toast.makeText(this@MainActivity, "Compra excluída", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
