package segoviano.bryan.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var cuenta: Int = 0
    lateinit var btn_sumar: Button
    lateinit var tv_cuenta: TextView
    lateinit var et_nombre: EditText
    var contara: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show()
        btn_sumar = findViewById(R.id.btn_clicker)
        tv_cuenta = findViewById(R.id.tv_cuenta)
        et_nombre = findViewById(R.id.txt_temaContador)
        val btn_restar: Button = findViewById(R.id.btn_restar)
        val btn_borrar: Button = findViewById(R.id.btn_borrar)
        val txt_contara: EditText = findViewById(R.id.txt_temaContador)

        btn_sumar.setOnClickListener {
            cuenta++
            tv_cuenta.setText("$cuenta")
            contara = txt_contara.text.toString()
        }

        btn_restar.setOnClickListener {
            cuenta--
            tv_cuenta.setText("$cuenta")
        }

        btn_borrar.setOnClickListener {
            borrar()
        }

    }

    fun borrar() {
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                        cuenta = 0
                        tv_cuenta.setText("$cuenta")
                        et_nombre.setText("")
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            // Set other dialog properties
            builder?.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onStart() {
        super.onStart()
//        Toast.makeText(this, "onstart", Toast.LENGTH_SHORT).show()
        val preferencias = this.getPreferences(Context.MODE_PRIVATE)
        cuenta = preferencias.getInt("key_cuenta", 0)
        contara = preferencias.getString("key_contara", "").toString()
        tv_cuenta.setText("$cuenta")
        et_nombre.setText("$contara")

    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(this, "onresume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
//        Toast.makeText(this, "onpause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()

        val preferencias = this.getPreferences(Context.MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putInt("key_cuenta", cuenta)
        editor.putString("key_contara", contara)
        editor.apply()
        Toast.makeText(this, "Se guardo el contador", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
//        Toast.makeText(this, "ondestroy", Toast.LENGTH_SHORT).show()
    }

}