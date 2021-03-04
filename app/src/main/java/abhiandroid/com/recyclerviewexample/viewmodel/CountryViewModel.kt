package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.Model.Country
import abhiandroid.com.recyclerviewexample.Utilities.Utils
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountryViewModel: ViewModel() {

    private val countryLivedata = MutableLiveData<Country>()

    fun getCountryData(context: Context): MutableLiveData<Country>{
        countryLivedata.value = Utils.parseJsonData(context)
        return countryLivedata
    }
}