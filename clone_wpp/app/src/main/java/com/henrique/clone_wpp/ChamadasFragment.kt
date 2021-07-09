package com.henrique.clone_wpp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChamadasFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chamadas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv =  view.findViewById<RecyclerView>(R.id.rvChamadas)

        val listaContato = mutableListOf<Contato>(
            Contato(nome="Bruno Ricardo", data="01 de julho 10:00 da Manhã"),
            Contato(nome="Henrique Pereira", data="02 de junho 10:00 da Tarde"),
            Contato(nome="Diogenes Paulino", data="03 de julho 10:00 da Noite"),
            Contato(nome="Rogério Alexandre", data="04 de julho 10:00 da Hibrido"),
            Contato(nome="Bruno Ricardo", data="01 de julho 10:00 da Manhã"),
            Contato(nome="Henrique Pereira", data="02 de junho 10:00 da Tarde"),
            Contato(nome="Diogenes Paulino", data="03 de julho 10:00 da Noite"),
            Contato(nome="Rogério Alexandre", data="04 de julho 10:00 da Hibrido"),
            Contato(nome="Bruno Ricardo", data="01 de julho 10:00 da Manhã"),
            Contato(nome="Henrique Pereira", data="02 de junho 10:00 da Tarde"),
            Contato(nome="Diogenes Paulino", data="03 de julho 10:00 da Noite"),
            Contato(nome="Rogério Alexandre", data="04 de julho 10:00 da Hibrido"),
            )

        rv.adapter = StatusAdapter(listaContato)
        rv.layoutManager = LinearLayoutManager(getContext())
    }
}