package com.example.ravn.ui.inicio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.ravn.R
import com.example.ravn.StarwarsQuery
import com.example.ravn.activity.StarWarsActivity
import com.example.ravn.adapter.PeopleStarWarsAdapter
import com.example.ravn.databse.ApolloConnector
import com.example.ravn.model.PeopleStarWars
import com.example.ravn.utils.disableSpinner
import com.example.ravn.utils.enableSpinner
import kotlinx.android.synthetic.main.fragment_people.*
import java.util.ArrayList

class PeopleFragment : Fragment(), PeopleStarWarsAdapter.Actions {
    private var afterCursor:String? = null
    lateinit var adapter: PeopleStarWarsAdapter
     private var pagina=1
    private var TotalPagina=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as StarWarsActivity)
        return inflater.inflate(R.layout.fragment_people, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enableSpinner()

        getUsers(view,pagina)
        //adapter = PeopleStarWarsAdapter(context!!, this)
    }

    override fun onClickItem(entidad: PeopleStarWars) {
        findNavController().navigate(R.id.nav_informacionFragment, bundleOf("entidad" to entidad))
    }
    fun getUsers(view:View, paginaEntrada:Int){
        adapter = PeopleStarWarsAdapter(requireContext(), this)


        val list2=ArrayList<PeopleStarWars>()

        ApolloConnector.myApolloClient.query(
                StarwarsQuery.builder().length(paginaEntrada*5)
                        .build()
        ).enqueue(object : ApolloCall.Callback<StarwarsQuery.Data>() {
            override fun onResponse(dataResponse: Response<StarwarsQuery.Data>) {
                //listUsers.clear() //clear list
                val users = dataResponse.data()?.allPeople()!!.edges()
                TotalPagina=dataResponse.data()!!.allPeople()!!.totalCount()!!
                if(TotalPagina%5==0){
                    TotalPagina=TotalPagina/5
                }
                else{
                    TotalPagina=(TotalPagina/5)+1
                }
                afterCursor = dataResponse.data()?.allPeople()!!.pageInfo().endCursor().toString()
                Log.e("FDsf", "no ingreso")
                users!!.forEach {
                    var item = it.node()
                    Log.e("FDsf", "ingreso al for")
                    if (item is StarwarsQuery.Node) { //item is automatically cast to user
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
                        val entidad = PeopleStarWars(listaInformacion,listaVehicle, item.name().toString(), resultado + " From " + item.homeworld()!!.name())

                        list2.add(entidad)
                        Log.e("FDsf", item.name().toString())
                    }
                }
                activity?.runOnUiThread {
                    val recyclerPendientes = view.findViewById<RecyclerView>(R.id.rvListaPeople)
                    recyclerPendientes.adapter = adapter
                    recyclerPendientes.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    adapter.setSolicitud(list2)
                    adapter.notifyDataSetChanged()
                    rvListaPeople.visibility=View.VISIBLE
                    disableSpinner()
                }
                activity?.runOnUiThread {
                    pagina = pagina+1
                    Thread.sleep(4*1000);
                    enableSpinner()
                    if(pagina<TotalPagina){
                        getUsers(view,pagina)
                    }
                    else{
                        disableSpinner()
                    }

                }
            }

            override fun onFailure(e: ApolloException) {
                //e.printStackTrace();
                activity?.runOnUiThread {
                    Log.d("TAG ERROR HTTP", e.message.toString())
                    fail.visibility = View.VISIBLE
                    rvListaPeople.visibility = View.GONE
                    disableSpinner()
                }
            }

        })

    }
    }