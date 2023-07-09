package com.example.vistas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vistas.R
import com.example.vistas.io.ApiService
import com.example.vistas.io.response.LoginResponse
import com.example.vistas.model.userDTO
import com.example.vistas.util.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var prefs: Prefs
    }

    private val apiService : ApiService by lazy{
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fr_inicio)
        prefs = Prefs(applicationContext)


        val botonLogin: Button = findViewById(R.id.btnIng)
        val botonReg: Button = findViewById(R.id.btnReg)

        botonLogin.setOnClickListener {
            performLogin()
        }

        botonReg.setOnClickListener {
            gotoRegistro()
        }
    }


    private fun performLogin(){
        val user_text = findViewById<EditText>(R.id.edtUser).text.toString()
        val pass_text = findViewById<EditText>(R.id.editPass).text.toString()
        println(user_text + " - " +pass_text);
        if(user_text.isNotEmpty() && pass_text.isNotEmpty() ){
            val cliente = userDTO(user_text,pass_text)
            val call = apiService.postLogin(cliente)
            println("CALL : " + call)
            call.enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    println("RESPONSE : " + response)
                    if (response.isSuccessful){
                        println(response)
                        val loginResponse = response.body()

                        if(loginResponse == null){
                            Toast.makeText(applicationContext,"se produjo un error en el servidor",Toast.LENGTH_LONG).show()
                            return
                        }
                        println(loginResponse.datos.id_cliente)
                        if(loginResponse.datos.id_cliente != null){

                            Toast.makeText(applicationContext,"sesion iniciada correctamente",Toast.LENGTH_LONG).show()
                            println(loginResponse)
                            prefs.saveId(loginResponse.datos.id_cliente)
                            prefs.saveName(loginResponse.datos.nombre)
                            prefs.saveUser(loginResponse.datos.usuario)
                            prefs.saveToken(loginResponse.datos.token)

                            val intent = Intent(applicationContext, Inicio::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(applicationContext,loginResponse.mensaje,Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    //manejo del error
                }

            })
        }else{
            showErrorName()
        }

    }

    private fun showErrorName(){
        Toast.makeText(this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show()
    }

    private fun gotoRegistro(){
        val intentRegistro = Intent(applicationContext, Registro::class.java)
        startActivity(intentRegistro)
    }
}