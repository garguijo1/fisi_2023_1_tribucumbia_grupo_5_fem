package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.PlatillosResponse
import com.example.vistas.io.response.ReservacionesResponse
import com.example.vistas.model.OnReservaClickListener
import com.example.vistas.model.PlatilloAdapter
import com.example.vistas.model.ReservasAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialReserva : AppCompatActivity(),OnReservaClickListener {

    private val apiService : ApiService by lazy{
        ApiService.create()
    }

    private val claseGlabal = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_historial_reservas)
        cargarHistorial()
    }

    fun cargarHistorial(){
        val id_usuario : Int = MainActivity.prefs.getId()
        val call = apiService.getReservasCliente(id_usuario ,14,"Bearer ${MainActivity.prefs.getToken()}")
        call.enqueue(object: Callback<ReservacionesResponse> {
            override fun onResponse(
                call: Call<ReservacionesResponse>,
                response: Response<ReservacionesResponse>
            ) {
                println(response)
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.listReservas)
                    recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    val datos = response.body()
                    if (datos != null) {
                        val data = datos.reservas
                        recyclerView.adapter = ReservasAdapter(data,claseGlabal)
                    }

                    println(datos)
                }
            }

            override fun onFailure(call: Call<ReservacionesResponse>, t: Throwable) {
                println("error en la peticion de platillos")
            }

        } )
    }

    override fun onClickDetallar(codigo: Int, fecha : String, sede : String, sillas : Int) {
        val intentDetalle = Intent(this,DetalleReserva::class.java)

        intentDetalle.putExtra("codigo",codigo)
        intentDetalle.putExtra("fecha",fecha)
        intentDetalle.putExtra("sede",sede)
        intentDetalle.putExtra("sillas",sillas)
        startActivity(intentDetalle)
    }

    override fun onClickAgregar(codigo: Int) {
        println("desde el historial : "+codigo)
        val intentAgregar = Intent(this,Menu::class.java)
        intentAgregar.putExtra("id_reservacion",codigo)
        startActivity(intentAgregar)
    }
}