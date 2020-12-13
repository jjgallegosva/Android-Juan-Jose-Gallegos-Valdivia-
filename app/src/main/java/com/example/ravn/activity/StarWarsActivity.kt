package com.example.ravn.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.apollographql.apollo.ApolloClient
import com.example.ravn.R
import com.example.ravn.RestauranteQuery
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class StarWarsActivity :AppCompatActivity() {
    private lateinit var navGraph: NavGraph
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Logger.addLogAdapter(AndroidLogAdapter())
        /*val apolloClient = ApolloClient.builder().build()
        apolloClient.query(RestauranteQuery.builder().after("dsds").length(2).build())*/
        //R.id.nav_inicioFragment

        //}

    }
}