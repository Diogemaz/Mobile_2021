package com.diogenes.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.content.*
import android.provider.MediaStore
import android.graphics.Bitmap

class MainActivity : AppCompatActivity() {
    lateinit var botaoTirarFoto: Button
    lateinit var foto: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botaoTirarFoto = findViewById(R.id.botao)
        botaoTirarFoto.setOnClickListener {
            abrirCamera()
        }
        foto = findViewById(R.id.imageView)
    }

    fun abrirCamera(){
        //INTENT IMPLÍCITA
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent, 12345)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 12345 && resultCode == RESULT_OK){
            val fotoTirada = data?.extras?.get("data") as Bitmap
            foto.setImageBitmap(fotoTirada)
        }
    }
}