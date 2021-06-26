package com.diogenes.googlekeep_copy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rvUsuarios)

        val lista = mutableListOf<anotacao>(
            anotacao(titulo="Tarefas", texto="levar o cachorro para passear, comprar um novo controle de TV"),
            anotacao(titulo="Para o café da manhã", texto="ovo, pão, leite e café"),
            anotacao(titulo="Escola", texto="Reunião amanhã"),
            anotacao(titulo="Trabalho", texto="Vai ser necessario uma nova tecnologia"),
        )

        rv.adapter = anotacaoAdapter(lista)

        // Exibe os itens em uma coluna única no padrão vertical
        //rv.layoutManager = LinearLayoutManager(this)

        // Exibe os itens em uma coluna única no padrão horizontal
        // rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Exibe os itens em uma tabela com x colunas
        rv.layoutManager = GridLayoutManager(this, 2)

        // Exibe os itens em uma tabela porém as células são ajustadas automaticamente de acordo com o conteúdo (Google Keep, Pintrest)
        // rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    }
}
