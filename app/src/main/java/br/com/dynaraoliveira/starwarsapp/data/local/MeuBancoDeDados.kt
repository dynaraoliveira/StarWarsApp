package br.com.dynaraoliveira.starwarsapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.dynaraoliveira.starwarsapp.data.local.converter.DateConverter
import br.com.dynaraoliveira.starwarsapp.data.local.dao.PersonDao
import br.com.dynaraoliveira.starwarsapp.data.local.entity.Person

@Database(entities = [Person::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MeuBancoDeDados: RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        private val INSTANCE: MeuBancoDeDados? = null
    }
}