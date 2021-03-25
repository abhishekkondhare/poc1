package abhiandroid.com.recyclerviewexample.repository

import abhiandroid.com.recyclerviewexample.network.RetrofitClient

open class DefaultCountryRepository : ICountryRepository {

    override fun getCountryData() = RetrofitClient.buildService().getCountryData()
}