package br.edu.scl.ifsp.ads.pdm.cdv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.cdv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object{
        const val VALOR_TELEFONE_UM = "VALOR_TELEFONE_UM"
        const val VALOR_TELEFONE_DOIS = "VALOR_TELEFONE_DOIS"
    }

    private val telefoneDoisEt: EditText by lazy {
        EditText(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)


        telefoneDoisEt.hint = "Segundo telefone"
        telefoneDoisEt.layoutParams= LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        amb.root.addView(telefoneDoisEt)

        amb.abraBt.setOnClickListener{
            // Jeito Kotlin para chamar a tela do activity_another.xml
            Intent(this, AnotherActivity::class.java).also {
                startActivity(it)
            }
            /* Jeito java de se fazer, eh a mesma coisa

            val anotherIntent: Intent  = Intent(this, AnotherActivity::class.java)
            startActivity(anotherIntent)*/
        }
        Log.v(getString(R.string.app_name), "onCreate: Iniciando ciclo completo")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(VALOR_TELEFONE_UM, amb.telefoneUmEt.text.toString())
        outState.putString(VALOR_TELEFONE_DOIS, telefoneDoisEt.text.toString())
        Log.v(getString(R.string.app_name), "onSaveInstanceState: Salvando dados de instancia")
    }

    // metodo chamado quando a tela for recriado
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

//        savedInstanceState.apply {
//            val valorTelefoen
//        }

        savedInstanceState.apply {
            getString(VALOR_TELEFONE_UM)?.let {  // só é executado se esse metodo nao retornar nulo
                amb.telefoneUmEt.setText(it)
            }
            getString(VALOR_TELEFONE_DOIS, "000000000").let {
                telefoneDoisEt.setText(it)
            }
        }
        Log.v(getString(R.string.app_name), "onRestoreInstanceState: Restaurando os15 dados de instancia")
    }

    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "onStart: Iniciando ciclo visivel")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "onResume: Iniciando ciclo foreground")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "onPause: Finalizando ciclo foreground")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "onStop: Finalizando ciclo visivel")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "onDestroy: Finalizando ciclo completo")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "onRestart: Preparando onStart")
    }
}