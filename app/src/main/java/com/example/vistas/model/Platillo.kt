package com.example.vistas.model

data class Platillo(
    val id_platillo : Int = 0,
    val nombre : String = "",
    val descripcion : String = "",
    val precio : Float = 0.00f,
    val foto : String = "",
    val id_categoria : Int = 0,
    val categoria : String = ""
)