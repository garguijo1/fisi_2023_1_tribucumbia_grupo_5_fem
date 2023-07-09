package com.example.vistas.model
/*
*         {
            "id_reservacion": 18,
            "sillas": 4,
            "atendido": 1,
            "sede": "Chacarilla",
            "ubicacion": "Av. Primavera 545 Chacarill, Lima - Perú",
            "fecha": "2022-12-02T22:00:00.000Z"
        },
* */
data class Reservas(
    val id_reservacion : Int,
    val sillas : Int,
    val atendido : Int,
    val sede : String,
    val ubicacion : String,
    val fecha : String,
    val hora : String,
)
