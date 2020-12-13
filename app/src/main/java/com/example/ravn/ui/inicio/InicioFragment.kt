package com.example.ravn.ui.inicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ravn.R
import com.example.ravn.activity.StarWarsActivity
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as StarWarsActivity)
        //entidadqryViewModel= ViewModelProvider(this).get(EntidadqryViewModel::class.java)
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener{
            findNavController().navigate(R.id.nav_peopleStarFragment)
        }
        //adapter = PeopleStarWarsAdapter(context!!, this)
    }

}