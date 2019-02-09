package br.com.dynaraoliveira.starwarsapp.data.repositories

import android.arch.lifecycle.LiveData
import br.com.dynaraoliveira.starwarsapp.data.local.dao.PersonDao
import br.com.dynaraoliveira.starwarsapp.data.local.entity.Person
import br.com.dynaraoliveira.starwarsapp.data.remote.PersonWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject
constructor(private val webservice: PersonWebService, private val personDao: PersonDao, private val executor: Executor) {

    fun getPerson(personId: String): LiveData<Person> {
        refreshPerson(personId)
        return personDao.load()
    }

    private fun refreshPerson(personId: String) {
        executor.execute {

            val personExists = personDao.hasPerson(getMaxRefreshTime(Date())) != null

            if (!personExists) {
                webservice.getPerson(personId).enqueue(object : Callback<Person> {
                    override fun onResponse(call: Call<Person>, response: Response<Person>) {
                        executor.execute {
                            val person = response.body()
                            person?.lastRefresh = Date()
                            if (person != null)
                                personDao.save(person)
                        }
                    }

                    override fun onFailure(call: Call<Person>, t: Throwable) {}
                })
            }
        }
    }

    private fun getMaxRefreshTime(currentDate: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = currentDate
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES)
        return cal.time
    }

    companion object {
        private const val FRESH_TIMEOUT_IN_MINUTES = 3
    }
}

