package abhiandroid.com.recyclerviewexample.network

import abhiandroid.com.recyclerviewexample.model.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface GetCountryData {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCountryData() : Observable<Country>
}