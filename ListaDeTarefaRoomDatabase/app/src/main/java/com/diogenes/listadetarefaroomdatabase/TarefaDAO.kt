package com.diogenes.listadetarefaroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TarefaDAO {
    @Query("SELECT * FROM TB_TAREFA")
    suspend fun getTarefas(): List<Tarefa>

    @Insert
    suspend fun addTarefas(t: Tarefa)

    @Delete
    suspend fun deleteTarefa(t: Tarefa)
}
