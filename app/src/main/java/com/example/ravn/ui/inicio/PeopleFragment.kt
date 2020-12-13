package com.example.ravn.ui.inicio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.ravn.R
import com.example.ravn.RestauranteQuery
import com.example.ravn.activity.StarWarsActivity
import com.example.ravn.adapter.PeopleStarWarsAdapter
import com.example.ravn.databse.ApolloConnector
import com.example.ravn.model.EntidadPasajero
import com.example.ravn.utils.disableSpinner
import com.example.ravn.utils.enableSpinner
import kotlinx.android.synthetic.main.fragment_people.*
import java.util.ArrayList

class PeopleFragment : Fragment(), PeopleStarWarsAdapter.Actions {
    private var afterCursor:String? = null
    lateinit var adapter: PeopleStarWarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as StarWarsActivity)
        //entidadqryViewModel= ViewModelProvider(this).get(EntidadqryViewModel::class.java)
        return inflater.inflate(R.layout.fragment_people, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*adapter = PeopleStarWarsAdapter(requireContext(), this)
        val recyclerPendientes = view.findViewById<RecyclerView>(R.id.rvListaPeople)
        recyclerPendientes.adapter=adapter
        recyclerPendientes.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val list=ArrayList<EntidadPasajero>()
        for (i in 0 until 10){
            val entidad = EntidadPasajero(i,"hola"+i,"juan"+i)
            list.add(entidad)
        }
        adapter.setSolicitud(list)*/
        enableSpinner()
        getUsers(view)
        //adapter = PeopleStarWarsAdapter(context!!, this)
    }

    override fun onClickItem(entidad: EntidadPasajero) {
        findNavController().navigate(R.id.nav_informacionFragment, bundleOf("entidad" to entidad))
    }
    fun getUsers(view:View){
        adapter = PeopleStarWarsAdapter(requireContext(), this)


        val list2=ArrayList<EntidadPasajero>()

        ApolloConnector.myApolloClient.query(
                RestauranteQuery.builder()
                        .build()
        ).enqueue(object : ApolloCall.Callback<RestauranteQuery.Data>() {
            override fun onResponse(dataResponse: Response<RestauranteQuery.Data>) {
                //listUsers.clear() //clear list
                val users = dataResponse.data()?.allPeople()!!.edges()
                afterCursor = dataResponse.data()?.allPeople()!!.pageInfo().endCursor().toString()
                Log.e("FDsf", "no ingreso")
                users!!.forEach {
                    var item = it.node()
                    Log.e("FDsf", "ingreso al for")
                    if (item is RestauranteQuery.Node) { //item is automatically cast to user
                        Log.e("FDsf", "ingreso al for e if")
                        val resultado = if (item.species()?.name() == null) "Human" else item.species()?.name().toString()
                        val listaInformacion=ArrayList<String>()
                        val listaVehicle = ArrayList<String>()

                        listaInformacion.add(item.eyeColor().toString())
                        listaInformacion.add(item.hairColor().toString())
                        listaInformacion.add(item.skinColor().toString())
                        listaInformacion.add(item.birthYear().toString())
                        for(i in 0 until item!!.vehicleConnection()!!.vehicles()!!.size){
                            listaVehicle.add(""+item.vehicleConnection()!!.vehicles()!!.get(i).name())
                        }
                        val entidad = EntidadPasajero(listaInformacion,listaVehicle, item.name().toString(), resultado + " From " + item.homeworld()!!.name())

                        list2.add(entidad)
                        //listUsers.add(User(item.name().toString(), item.avatarUrl(), item.location().toString(), item.login().toString()))
                        Log.e("FDsf", item.name().toString())
                    }
                }

                activity?.runOnUiThread {


                    val recyclerPendientes = view.findViewById<RecyclerView>(R.id.rvListaPeople)
                    recyclerPendientes.adapter = adapter
                    recyclerPendientes.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    adapter.setSolicitud(list2)
                    adapter.notifyDataSetChanged()
                    disableSpinner()
                }

                // onResponse returns on a background thread. If you want to make UI updates make sure they are done on the Main Thread.
                /*activity?.runOnUiThread {
                    //emptyview
                    var emptyView = viewOfLayout.findViewById(R.id.emptyView) as TextView
                    var RecycleUsers = viewOfLayout.findViewById(R.id.lrvUsers) as RecyclerView

                    if (listUsers.isEmpty()) {
                        RecycleUsers.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    else {
                        RecycleUsers.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    }
                    mAdapter.notifyDataSetChanged() //Notify change
                }*/
            }

            override fun onFailure(e: ApolloException) {
                //e.printStackTrace();
                Log.d("TAG ERROR HTTP", e.message.toString())
            }
        })
    }
    }