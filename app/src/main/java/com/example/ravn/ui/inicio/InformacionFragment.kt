package com.example.ravn.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ravn.R
import com.example.ravn.activity.StarWarsActivity
import com.example.ravn.adapter.InformationAdapter
import com.example.ravn.adapter.PeopleStarWarsAdapter
import com.example.ravn.model.EntidadPasajero
import com.example.ravn.model.Informacion
import java.util.ArrayList


class InformacionFragment : Fragment() {

    lateinit var adapter:InformationAdapter
    lateinit var probar:EntidadPasajero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as StarWarsActivity)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacion, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = InformationAdapter(requireContext())
        val recyclerPendientes = view.findViewById<RecyclerView>(R.id.lvItemsinformacion)
        recyclerPendientes.adapter=adapter
        recyclerPendientes.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val list= ArrayList<Informacion>()
        probar=requireArguments().get("entidad")!! as EntidadPasajero
        for (i in 0 until 10){
            val entidad = Informacion(probar.contenido,probar.titulo)
            list.add(entidad)
        }
        adapter.setSolicitud(list)
        //adapter = PeopleStarWarsAdapter(context!!, this)
    }




}