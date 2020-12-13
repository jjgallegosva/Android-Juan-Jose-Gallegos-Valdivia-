package com.example.ravn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ravn.R
import com.example.ravn.model.Vehicle

class VehicleAdapter (private val context: Context) : RecyclerView.Adapter<VehicleAdapter.WordViewHolder>() {

    var dataList = ArrayList<Vehicle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_vehicle,
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
        var tvTitulo = itemView.findViewById(R.id.descripcion) as TextView


        fun setData(solicitud: Vehicle) {
            //imgfoto.setImageResource(solicitud.getImgFoto())
            tvTitulo.text = solicitud.text


        }
    }

    internal fun setVehicle(envios: List<Vehicle>) {
        dataList.clear()
        dataList.addAll(envios)
        notifyDataSetChanged()
    }
}