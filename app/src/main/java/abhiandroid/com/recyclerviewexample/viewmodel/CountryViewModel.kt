package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.repository.CountryRepository
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountryViewModel: ViewModel() {

    val countryLivedata = MutableLiveData<Country>()
    private val countryRepository = CountryRepository()

    fun getCountryData(context: Context){
        countryLivedata.value = countryRepository.getCountryData(context)
    }
}