package com.example.ravn.databse

import com.apollographql.apollo.ApolloClient
import com.example.ravn.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class ApolloConnector {

    companion object {
        private  val BASE_URL:String="https://swapi-graphql.netlify.app/.netlify/functions/index"
        val myApolloClient: ApolloClient =
                getApolloClient()



        private fun getApolloClient() : ApolloClient{

            var interceptorLog = HttpLoggingInterceptor()
            interceptorLog.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptorLog)
                    .addNetworkInterceptor { chain ->
                        val request = chain.request().newBuilder()
                                .build()

                        chain.proceed(request)
                    }
                    .build()

            return ApolloClient.builder()
                    .serverUrl(BASE_URL)
                    .okHttpClient(okHttpClient)
                    .build()
        }




    }

}