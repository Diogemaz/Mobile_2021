package com.diogenes.listadecomprasroomdatabases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ComprasDAO {
    @Query("SELECT * FROM TB_COMPRAS")
    suspend fun getCompras(): List<Compras>

    @Insert
    suspend fun addCompras(t: Compras)

    @Delete
    suspend fun deleteCompras(t: Compras)
}
