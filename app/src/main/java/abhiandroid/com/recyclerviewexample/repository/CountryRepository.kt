package abhiandroid.com.recyclerviewexample.repository

import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.utilities.Utils
import android.content.Context

class CountryRepository {

    fun getCountryData(context: Context): Country?{
        return Utils.parseJsonData(context)
    }
}