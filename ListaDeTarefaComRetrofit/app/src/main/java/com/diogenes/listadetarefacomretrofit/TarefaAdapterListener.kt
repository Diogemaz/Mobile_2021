package com.diogenes.listadetarefacomretrofit

import com.diogenes.listadetarefacomretrofit.data.model.Tarefa

interface TarefaAdapterListener {
    fun excluirTarefa(tarefa: Tarefa)
}
