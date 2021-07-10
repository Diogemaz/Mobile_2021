package com.bruno.cadastrotarefasfoto

import android.graphics.Bitmap

class Tarefa (
    var foto: Bitmap?=null,
    var descricao: String,
    var prioridade: Prioridade,
    var perfil: Perfil,
    var urgente: Boolean
)