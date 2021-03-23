package abhiandroid.com.recyclerviewexample.repository

import abhiandroid.com.recyclerviewexample.model.Country
import io.reactivex.Observable

interface ICountryRepository {
    fun getCountryData(): Observable<Country>
}