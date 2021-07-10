package com.diogenes.listadecomprasroomdatabases


import androidx.room.*

@Entity(tableName = "TB_COMPRAS")
data class Compras(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo(name="Nome")
    val nome: String,
)
