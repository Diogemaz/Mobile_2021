package com.rogerio.aula11.data.model

import com.google.gson.annotations.SerializedName

class Cep(@SerializedName("logradouro") val logradouro: String,
          @SerializedName("bairro")val bairro: String,
          @SerializedName("localidade")val cidade: String,
          @SerializedName("uf")val uf: String) {

    //Sobrecarga de método
    override fun toString() : String {
        return "Rua: $logradouro\nBairro:$bairro\nE fica em: $cidade / $uf"
    }

}