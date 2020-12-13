package com.example.ravn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ravn.R
import com.example.ravn.model.EntidadPasajero
import com.example.ravn.model.Informacion

class InformationAdapter(private val context: Context) : RecyclerView.Adapter<InformationAdapter.WordViewHolder>() {
    var dataList = ArrayList<Informacion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_informacion,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.setData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    inner class WordViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)  {
        //var imgfoto = itemView.findViewById(R.id.icBook) as ImageView
        var tvTitulo = itemView.findViewById(R.id.nombre) as TextView
        var tvContenido = itemView.findViewById(R.id.descripcion) as TextView

        fun setData(solicitud: Informacion) {
            //imgfoto.setImageResource(solicitud.getImgFoto())
            tvTitulo.text = solicitud.textt
            tvContenido.text = solicitud.textt2

        }
    }

    internal fun setSolicitud(envios: List<Informacion>) {
        dataList.clear()
        dataList.addAll(envios)
        notifyDataSetChanged()
    }
}