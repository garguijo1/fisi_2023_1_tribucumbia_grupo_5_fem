package com.example.vistas.io

import com.example.vistas.io.response.*
import com.example.vistas.model.AgregarReserva
import com.example.vistas.model.ReservacionModel
import com.example.vistas.model.UserModel
import com.example.vistas.model.userDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    @POST( value = "ne-gestion-cliente/bstk/servicio-al-cliente/v1/login")
    fun postLogin(@Body userDTO : userDTO):
            Call<LoginResponse>

    @POST( value = "ne-gestion-cliente/bstk/servicio-al-cliente/v1/crear-cliente")
    fun postCliente(@Body userModel: UserModel ):
            Call<MensajeResponse>

    @Headers("Content-Type: application/json")
    @POST( value = "ne-gestion-reservaciones/bstk/servicio-al-cliente/v1/agregar-platillos-reservacion")
    fun postAgregarPlatilloReservacion(
        @Body agregarReserva: AgregarReserva,
        @Header("Authorization") token: String?
    ): Call<MensajeResponse>

    @Headers("Content-Type: application/json")
    @POST( value = "ne-gestion-reservaciones/bstk/servicio-al-cliente/v1/crear-reservacion")
    fun postReservacion(
        @Query( value = "accion") accion : Int,
        @Body reservaModel: ReservacionModel ,
        @Header("Authorization") token: String?
    ): Call<ReservaCreatedResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "ne-gestion-platillos/bstk/servicio-al-cliente/v1/listar-platillos")
    fun getPlatillos(
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<PlatillosResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "ne-gestion-sedes/bstk/servicio-al-cliente/v1/listar-sedes")
    fun getSedes(
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<SedesResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "ne-gestion-reservaciones/bstk/servicio-al-cliente/v1/listar-reservaciones-cliente/{id}")
    fun getReservasCliente(
        @Path( value = "id") id : Int,
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<ReservacionesResponse>

    @Headers("Content-Type: application/json")
    @GET( value = "ne-gestion-reservaciones/bstk/servicio-al-cliente/v1/detallar/{id}")
    fun getDetalleReserva(
        @Path( value = "id") id : Int,
        @Query( value = "accion") accion : Int,
        @Header("Authorization") token: String?
    ):  Call<PlatilloDetalleResponse>


    companion object Factory{
        private const val BASE_URL = "http://10.0.2.2:9000/"
        //private const val BASE_URL = "http://172.17.0.2:9000/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

