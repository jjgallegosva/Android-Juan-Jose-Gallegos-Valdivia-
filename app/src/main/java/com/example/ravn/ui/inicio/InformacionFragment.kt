package com.example.ravn.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ravn.R
import com.example.ravn.activity.StarWarsActivity
import com.example.ravn.adapter.InformationAdapter
import com.example.ravn.adapter.VehicleAdapter
import com.example.ravn.model.Informacion
import com.example.ravn.model.PeopleStarWars
import com.example.ravn.model.Vehicle
import kotlinx.android.synthetic.main.fragment_informacion.*
import java.util.ArrayList


class InformacionFragment : Fragment() {

    lateinit var adapter:InformationAdapter
    lateinit var adapterVe:VehicleAdapter
    lateinit var probar: PeopleStarWars
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
        adapterVe = VehicleAdapter(requireContext())
        val recyclerPendientes = view.findViewById<RecyclerView>(R.id.lvItemsinformacion)
        val recyclerVehicles = view.findViewById<RecyclerView>(R.id.lvlvehicles)
        recyclerPendientes.adapter=adapter
        recyclerVehicles.adapter=adapterVe
        recyclerPendientes.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerVehicles.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val list= ArrayList<Informacion>()
        val vehicleList = ArrayList<Vehicle>()
        probar=requireArguments().get("entidad")!! as PeopleStarWars
        val arregloNombre = arrayOf("Eye Color","Hair Color","Skin Color", "Birth Year")
        for (i in 0 until probar.informacion.size){
            val entidad=Informacion(probar.informacion.get(i),arregloNombre.get(i))
            list.add(entidad)
        }
        adapter.setSolicitud(list)
        textView2.text=probar.titulo
        if(probar.vehicle.size<=0){
            vehicles.visibility=View.GONE
        }
        for(i in 0 until probar.vehicle.size){
            val vehicle=Vehicle(probar.vehicle.get(i))
            vehicleList.add(vehicle)
        }
        adapterVe.setVehicle(vehicleList)
        goBack.setOnClickListener{
            findNavController().navigate(R.id.nav_peopleStarFragment)
        }
    }

}