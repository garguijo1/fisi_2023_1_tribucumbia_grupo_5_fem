package com.example.vistas.ui

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.vistas.R
import com.example.vistas.model.Platillo
import com.example.vistas.model.PlatilloFinal
import com.example.vistas.model.ReservaCarta
import com.example.vistas.util.Global

class AgregarPlatillo : AppCompatActivity() {

    private var cantidad : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_carta_elegir)
        cargarInformacion()

        val btnDown = findViewById<Button>(R.id.button3)
        val btnUp = findViewById<Button>(R.id.button4)
        val text_cantidad = findViewById<TextView>(R.id.textView6)

        val btnAgregar = findViewById<Button>(R.id.buttonAgregarResCarEle)

        btnDown.setOnClickListener {
            if(this.cantidad > 1){
                this.cantidad = this.cantidad - 1
                text_cantidad.text = this.cantidad.toString()
            }else{
                Toast.makeText(applicationContext,"como minimo debe tener un elemento", Toast.LENGTH_SHORT).show()
            }
        }

        btnUp.setOnClickListener {
            this.cantidad = this.cantidad + 1
            text_cantidad.text = this.cantidad.toString()
        }

        btnAgregar.setOnClickListener {
            agregarPlatillo()
        }

    }

    fun cargarInformacion(){
        val nombre = findViewById<TextView>(R.id.textView11)
        val descripcion = findViewById<TextView>(R.id.textView16)
        val foto = findViewById<ImageView>(R.id.imageView2)

        val cantidad = findViewById<TextView>(R.id.textView6)

        cantidad.text = this.cantidad.toString()

        nombre.text = intent.getStringExtra("nombre")
        descripcion.text = intent.getStringExtra("descripcion")

        val base64String = intent.getStringExtra("foto").toString()
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        foto.setImageBitmap(decodedByte)
    }

    fun  agregarPlatillo(){

        val id_reservacion = intent.getIntExtra("id_reservacion",0)
        println("desde el agregar : "+id_reservacion)

        val id_platillo = intent.getIntExtra("id_platillo",0)
        val nombre = intent.getStringExtra("nombre").toString()
        val precio = intent.getFloatExtra("precio",0.0f)
        val foto = intent.getStringExtra("foto").toString()

        val intentFinal = Intent(applicationContext, Final::class.java)

        val platilloFinal = PlatilloFinal(id_platillo,nombre,precio,foto,this.cantidad);
        Global.platillosSeleccionados.add(platilloFinal)

        intentFinal.putExtra("id_reservacion",id_reservacion)

        startActivity(intentFinal)
    }
}