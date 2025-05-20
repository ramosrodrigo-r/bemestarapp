package br.com.rodrigo.bemestarapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: CheckInApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://682ce7d24fae188947543cbb.mockapi.io/api/v1/") // Substitua pela URL real se houver
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CheckInApi::class.java)
    }
}