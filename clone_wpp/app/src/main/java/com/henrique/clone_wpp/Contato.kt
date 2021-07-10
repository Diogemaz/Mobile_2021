package com.henrique.clone_wpp

import android.graphics.drawable.Drawable

data class Contato(
    var img: Drawable?=null,
    var nome: String,
    var mensagem: String?=null,
    var data: String
)
