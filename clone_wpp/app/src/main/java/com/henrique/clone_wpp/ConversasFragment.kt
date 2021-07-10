package com.henrique.clone_wpp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConversasFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv =  view.findViewById<RecyclerView>(R.id.rvContatos)

        val listaContato = mutableListOf<Contato>(
            Contato(nome="Henrique Pereira de Souza", data="23:59 da Manhã", mensagem="A melhor maneira de resolver um rush é rushando!"),
            Contato(nome="Bruno Ricardo dos Santos Melo Costa", data="10:00 da Manhã", mensagem="Temos que iniciar o processo de abertura das aulas presenciais o mais rapido possivel! #rush"),
            Contato(nome="Diogenes Paulino da Silva", data="08:30 da Manhã", mensagem="Não a rush que não consiga ser rushado."),
            Contato(nome="Rogério Alexandre Gongora", data="10:10 da Manhã", mensagem="Quero rush longe da minha vida!"),
            Contato(nome="Rogério Alexandre Gongora", data="10:10 da Manhã", mensagem="Quero rush longe da minha vida!"),

        )

        rv.adapter = ContatoAdapter(listaContato)
        rv.layoutManager = LinearLayoutManager(getContext())
    }
}