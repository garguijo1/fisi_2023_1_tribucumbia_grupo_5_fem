package com.example.vistas.model

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R

class PlatilloFinalViewHolder(view : View) : RecyclerView.ViewHolder(view){

    val nombrePLatillo = view.findViewById<TextView>(R.id.tvNombrePlatillo)
    val cantidad = view.findViewById<TextView>(R.id.tvCantidad)
    val precio = view.findViewById<TextView>(R.id.tvPrecioPlatillo)
    val total = view.findViewById<TextView>(R.id.tvTotal)
    val fotoPLatillo = view.findViewById<ImageView>(R.id.ivPlatillo)


    fun render(detalleModel : PlatilloFinal){
        nombrePLatillo.text = detalleModel.nombre
        cantidad.text = "x ${detalleModel.cantidad.toString()}"
        precio.text = detalleModel.precio.toString()
        val resulado = (detalleModel.cantidad * detalleModel.precio)
        total.text = (resulado).toString()

        val base64String = detalleModel.foto
        val base64Image = base64String.split(",").toTypedArray()[1]

        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        fotoPLatillo.setImageBitmap(decodedByte)

    }

}
