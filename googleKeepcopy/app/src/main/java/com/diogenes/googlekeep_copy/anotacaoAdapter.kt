package com.diogenes.googlekeep_copy

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class anotacaoAdapter(var listaAnotacoes: MutableList<anotacao>):RecyclerView.Adapter<anotacaoAdapter.ItemViewHolder>() {

    // Responsável por encontrar os componentes dentro do layout (item_usuario) e indicar que o
    //  layout será replicado na Recycler View
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtTitulo: TextView = view.findViewById(R.id.titulo)
        val txtTexto: TextView = view.findViewById(R.id.texto)
    }

    // Responsável por inflar (fazer aparecer) o layout por exemplo na activity ou fragment ou dialog (tudo que extends View Group)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anotacoes, parent, false)
        return ItemViewHolder(view)
    }

    // Vincula os componentes do layout (item_usuario) a um dado/propriedade/atributo da lista
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.txtTitulo.text = listaAnotacoes[position].titulo
        holder.txtTexto.text = listaAnotacoes[position].texto
    }

    // Indica quantos registros/itens a lista possui
    override fun getItemCount(): Int {
        return listaAnotacoes.size
    }
}
