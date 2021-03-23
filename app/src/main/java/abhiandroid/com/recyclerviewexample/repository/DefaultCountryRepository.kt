package abhiandroid.com.recyclerviewexample.repository

import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.network.RetrofitClient
import io.reactivex.Observable

open class DefaultCountryRepository : ICountryRepository {

    override fun getCountryData(): Observable<Country> {
        return RetrofitClient.buildService().getCountryData()
    }

}