package com.henrique.clone_wpp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StatusFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv =  view.findViewById<RecyclerView>(R.id.rvStatus)

        val listaContato = mutableListOf<Contato>(
            Contato(nome="HDB", data="12:15 da Manhã"),
            Contato(nome="Vocational", data="11:47 da Noite"),
            Contato(nome="Bruno Ricardo", data="00:01 da Hibrido"),
            Contato(nome="Diogenes Paulino", data="02:40 da Outros"),
            Contato(nome="Rogério Alexandre", data="03:10 da Teste"),
            Contato(nome="Henrique Pereira", data="20:00 da ???")

        )

        rv.adapter = StatusAdapter(listaContato)
        rv.layoutManager = LinearLayoutManager(getContext())
    }
}