package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.vistas.R
import com.example.vistas.ui.MainActivity.Companion.prefs

class CheckCarta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_carta_confirmada)

        val buttonMesaConfInicio = findViewById<Button>(R.id.buttonCartaConfInicio)
        cargarInfo()
        buttonMesaConfInicio.setOnClickListener {
            gotoInicio()
        }

    }

    fun gotoInicio(){
        val intentInicio = Intent(this,Inicio::class.java)

        startActivity(intentInicio)
    }

    fun cargarInfo(){
        val usuario = findViewById<TextView>(R.id.textViewHolaUCarta)
        usuario.text = prefs.getName()
    }

}