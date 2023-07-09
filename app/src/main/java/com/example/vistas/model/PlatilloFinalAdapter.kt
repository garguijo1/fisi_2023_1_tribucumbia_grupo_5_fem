package com.example.vistas.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistas.R

class PlatilloFinalAdapter(val platillosList : List<PlatilloFinal>) :
    RecyclerView.Adapter<PlatilloFinalViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloFinalViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return PlatilloFinalViewHolder(layoutInflater.inflate(R.layout.item_platillo_final,parent,false))
        }

        override fun onBindViewHolder(holder: PlatilloFinalViewHolder, position: Int) {
            val item = platillosList[position]
            holder.render(item)
        }

        override fun getItemCount(): Int {
            return platillosList.size
        }
}