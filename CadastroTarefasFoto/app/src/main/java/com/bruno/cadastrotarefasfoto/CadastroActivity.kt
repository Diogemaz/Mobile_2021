package com.bruno.cadastrotarefasfoto

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*

class CadastroActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var prioridade: Prioridade
    lateinit var perfil: Perfil
    lateinit var spnPerfil: Spinner
    lateinit var edtDescricao: EditText
    lateinit var btnSalvar: Button
    lateinit var imvFoto: ImageView
    var fotoTirada: Bitmap?=null
    lateinit var swtUrgente: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        edtDescricao = findViewById(R.id.edtDescricao)
        spnPerfil = findViewById(R.id.spnPerfil)
        btnSalvar = findViewById(R.id.btnSalvar)
        imvFoto = findViewById(R.id.imvFoto)
        swtUrgente = findViewById(R.id.swtUrgente)

        // Intent para chamar a Câmera
        imvFoto.setOnClickListener(){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager) != null)
                startActivityForResult(intent, RC_CAMERA)
        }

        // Aqui estamos implementando o "Adapter" do Spinner (Combo), consumindo o array no arquivo strings.xml
        ArrayAdapter.createFromResource(this, R.array.opcoes_spinner, android.R.layout.simple_spinner_item).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnPerfil.adapter = arrayAdapter
        }

        spnPerfil.onItemSelectedListener = this

        btnSalvar.setOnClickListener(){
            val usuario = Tarefa(fotoTirada, edtDescricao.text.toString(), prioridade, perfil, swtUrgente.isChecked)
            MainActivity.lista.add(usuario)
            finish()
        }
    }

    fun OnStackClick(view: View){
        if (view is RadioButton){
            var checked = view.isChecked

            when (view.id){
                R.id.rdbBaixa -> { if (checked) {prioridade = Prioridade.BAIXA}}
                R.id.rdbMedia -> { if (checked) {prioridade = Prioridade.BAIXA}}
                R.id.rdbAlta -> { if (checked) {prioridade = Prioridade.ALTA}}
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p2){
            0 -> {perfil = Perfil.PESSOAL}
            1 -> {perfil = Perfil.PROFISSIONAL}
            2 -> {perfil = Perfil.AMBOS}
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((resultCode == RESULT_OK) && (requestCode == RC_CAMERA)){
            fotoTirada = data?.extras?.get("data") as Bitmap
            imvFoto.setImageBitmap(fotoTirada)
        }
    }

    companion object{
        const val RC_CAMERA  = 12345
        const val RC_GALERIA = 67890
    }
}