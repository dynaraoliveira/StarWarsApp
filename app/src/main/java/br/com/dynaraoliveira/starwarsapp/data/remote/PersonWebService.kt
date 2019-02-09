package br.com.dynaraoliveira.starwarsapp.data.remote

import br.com.dynaraoliveira.starwarsapp.data.local.entity.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PersonWebService {
    @GET("/people/{id}")
        fun getPerson(@Path("id") personId: String): Call<Person>
}