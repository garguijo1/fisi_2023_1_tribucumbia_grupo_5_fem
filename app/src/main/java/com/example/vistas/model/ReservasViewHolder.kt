package com.example.vistas.model


import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R


class ReservasViewHolder(val view : View,val listener : OnReservaClickListener) : RecyclerView.ViewHolder(view){

    val cod_reserva = view.findViewById<TextView>(R.id.cod_reserva)
    val fecha_reserva = view.findViewById<TextView>(R.id.TextViewDataHoraReserva)
    val atentido_reserva = view.findViewById<TextView>(R.id.textViewDataFechaReserva)

    val btnDetallar =  view.findViewById<Button>(R.id.detalla_reserva)
    val btnAgregar =  view.findViewById<Button>(R.id.reserva_platillo)



    fun render(reservasModel : Reservas){

        btnAgregar.setOnClickListener { listener.onClickAgregar(reservasModel.id_reservacion) }
        btnDetallar.setOnClickListener { listener.onClickDetallar(reservasModel.id_reservacion,"${reservasModel.fecha} - ${reservasModel.hora}",reservasModel.sede,reservasModel.sillas) }

        cod_reserva.text = "#${reservasModel.id_reservacion}"
        fecha_reserva.text= "${reservasModel.fecha} - ${reservasModel.hora}"
        if(reservasModel.atendido == 1){
            atentido_reserva.text = "Atendido"
        }else{
            atentido_reserva.text = "Pendiente"
        }
    }

}