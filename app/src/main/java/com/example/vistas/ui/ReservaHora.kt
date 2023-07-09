package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.MensajeResponse
import com.example.vistas.io.response.ReservaCreatedResponse
import com.example.vistas.model.ReservacionModel
import com.example.vistas.model.UserModel
import com.example.vistas.ui.MainActivity.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservaHora : AppCompatActivity() {

    private var horaReserva : String = "16:00:00"
    private var cantidad : Int = 1

    private val apiService : ApiService by lazy{
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_reserva_mesa_hora)
        val btn4 = findViewById<Button>(R.id.buttonHora1)
        val btn5 = findViewById<Button>(R.id.buttonHora2)
        val btn6 = findViewById<Button>(R.id.buttonHora3)
        val btn7 = findViewById<Button>(R.id.buttonHora4)
        val btn8 = findViewById<Button>(R.id.buttonHora5)
        val btn9 = findViewById<Button>(R.id.buttonHora6)

        val buttonResMesaHora = findViewById<Button>(R.id.buttonResMesaHora)

        val btnMenos = findViewById<ImageView>(R.id.imageView5)
        val btnMas = findViewById<ImageView>(R.id.imageView4)
        val tvCantidad = findViewById<TextView>(R.id.textView2)


        btn4.setOnClickListener { this.horaReserva = "16:00:00"; modificarfecha("16:00:00") }
        btn5.setOnClickListener { this.horaReserva = "17:00:00"; modificarfecha("17:00:00") }
        btn6.setOnClickListener { this.horaReserva = "18:00:00"; modificarfecha("18:00:00") }
        btn7.setOnClickListener { this.horaReserva = "19:00:00"; modificarfecha("19:00:00") }
        btn8.setOnClickListener { this.horaReserva = "20:00:00"; modificarfecha("20:00:00") }
        btn9.setOnClickListener { this.horaReserva = "21:00:00"; modificarfecha("21:00:00") }

        cargarDatos()

        buttonResMesaHora.setOnClickListener {
            guardarReserva()
        }

        btnMenos.setOnClickListener {
            if(this.cantidad > 1){
                this.cantidad = this.cantidad - 1
                tvCantidad.text = this.cantidad.toString()
            }else{
                Toast.makeText(applicationContext,"no se puede reducir la cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        btnMas.setOnClickListener {
            if(this.cantidad < 8){
                this.cantidad = this.cantidad + 1
                tvCantidad.text = this.cantidad.toString()
            }else{
                Toast.makeText(applicationContext,"el limite es 8 personas por mesa", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun guardarReserva(){
        val sillas : Int = this.cantidad
        val id_cliente : Int = prefs.getId()
        val id_sede : Int = intent.getIntExtra("id_sede",0)
        val fecha :String = "${intent.getStringExtra("fecha")}"
        val hora : String = this.horaReserva
        val fecha_envio :String = "${intent.getStringExtra("fecha")}  ${this.horaReserva}"

        val reserva_registro = ReservacionModel(sillas,id_cliente,id_sede,fecha,hora)

        println(reserva_registro)


        val call = apiService.postReservacion(
            12,
            reserva_registro,
            "Bearer ${prefs.getToken()}"
        )

        println(prefs.getToken())

        call.enqueue(object: Callback<ReservaCreatedResponse> {
            override fun onResponse(
                call: Call<ReservaCreatedResponse>,
                response: Response<ReservaCreatedResponse>
            ) {
                println(response)

                if (response.isSuccessful){

                    val responseReservacion = response.body()

                    if( responseReservacion == null){
                        Toast.makeText(applicationContext,"se produjo un error en el servidor",
                            Toast.LENGTH_LONG).show()
                        return
                    }else{
                        Toast.makeText(applicationContext, responseReservacion.mensaje,
                            Toast.LENGTH_LONG).show()
                        val intentCheck = Intent(applicationContext, CheckMesa::class.java)
                        val nombre_sede = intent.getStringExtra("sede")
                        intentCheck.putExtra("sede",nombre_sede)
                        intentCheck.putExtra("cantidad",sillas)
                        intentCheck.putExtra("fecha",fecha_envio)
                        startActivity(intentCheck)
                    }
                }
            }

            override fun onFailure(call: Call<ReservaCreatedResponse>, t: Throwable) {

                Toast.makeText(applicationContext,"se produjo un error en el guardado de la reservacion",
                    Toast.LENGTH_LONG).show()
                return
            }

        })
    }

    fun cargarDatos(){
        val tvFecha = findViewById<TextView>(R.id.textViewMenMesahora)
        tvFecha.setText("${intent.getStringExtra("fecha")} - ${this.horaReserva}")
    }

    fun modificarfecha(hora : String){
        val tvFecha = findViewById<TextView>(R.id.textViewMenMesahora)
        tvFecha.setText("${intent.getStringExtra("fecha")} - ${hora}")
    }

}