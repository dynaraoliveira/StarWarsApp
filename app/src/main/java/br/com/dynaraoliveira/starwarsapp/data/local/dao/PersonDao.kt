package br.com.dynaraoliveira.starwarsapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.dynaraoliveira.starwarsapp.data.local.entity.Person
import java.util.*

@Dao
interface PersonDao {

    @Insert(onConflict = REPLACE)
    fun save(person: Person)

    @Query("SELECT * FROM Person LIMIT 1")
    fun load(): LiveData<Person>

    @Query("SELECT * FROM Person WHERE lastRefresh = :lastRefresh LIMIT 1")
    fun hasPerson(lastRefresh: Date): Person

}