package com.example.vistas.model

/*
*{
    "accion" : 12,
    "sillas" : 4,
    "id_cliente" : 7,
    "id_sede" : 2,
    "fecha" : "2023-01-05 20:00:00"
}
* */

data class ReservacionModel(
    val sillas : Int,
    val id_cliente : Int,
    val id_sede : Int,
    val fecha : String,
    val hora : String
)