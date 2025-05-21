package br.com.rodrigo.bemestarapp.data.remote

import br.com.rodrigo.bemestarapp.domain.model.Tip
import retrofit2.http.GET


interface CheckApi {
    @GET("tips")
    suspend fun getTips(): List<Tip>

    @GET("feedback")
    suspend fun getSupportMessage(): Tip
}
