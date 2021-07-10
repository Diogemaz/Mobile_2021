package com.bruno.cadastrotarefasfoto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(var listaTarefa: MutableList<Tarefa>, var context: Context): RecyclerView.Adapter<TarefaAdapter.ItemViewHolder>() {

    // Responsável por encontrar os componentes dentro do layout (item_usuario) e indicar que o
    //  layout será replicado na Recycler View
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgFoto: ImageView = view.findViewById(R.id.imgFoto)
        val txtDescricao: TextView = view.findViewById(R.id.txtDescricao)
        val txtPrioridade: TextView = view.findViewById(R.id.txtPrioridade)
        val txtPerfil: TextView = view.findViewById(R.id.txtPerfil)
    }

    // Responsável por inflar (fazer aparecer) o layout por exemplo na activity ou fragment ou dialog (tudo que extends View Group)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefa, parent, false)
        return ItemViewHolder(view)
    }

    // Vincula os componentes do layout (item_usuario) a um dado/propriedade/atributo da lista
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        listaTarefa[position].foto?.let{
            holder.imgFoto.setImageBitmap(it)
        }
        holder.txtDescricao.text = listaTarefa[position].descricao
        holder.txtPrioridade.text = listaTarefa[position].prioridade.nome
        holder.txtPerfil.text = listaTarefa[position].perfil.nome

        // Implementando o "click" do CardView para retornar um Toast como mensagem
        holder.itemView.setOnClickListener(){
            var texto = if (listaTarefa[position].urgente) {
                "Tarefa Urgente!"
            }
            else {
                "Tarefa Suave"
            }

            Toast.makeText(context, texto, Toast.LENGTH_SHORT).show()
        }
    }

    // Indica quantos registros/itens a lista possui
    override fun getItemCount(): Int {
        return listaTarefa.size
    }
}