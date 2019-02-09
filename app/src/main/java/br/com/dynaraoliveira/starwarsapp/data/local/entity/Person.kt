package br.com.dynaraoliveira.starwarsapp.data.local.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Person(
        var name: String,
        var height:String,
        var mass: String,
        @SerializedName("hair_color")
        var hairColor: String,
        @SerializedName("eye_color")
        var eyeColor: String,
        var gender: String,
        var lastRefresh: Date? = null
)