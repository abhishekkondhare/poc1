package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.repository.DefaultCountryRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


private const val TAG = "CountryViewModel"
class CountryViewModel: ViewModel() {
    val countryLivedata = MutableLiveData<Country>()
    private val countryRepository = DefaultCountryRepository()
    private val compositeDisposable = CompositeDisposable()

    fun getCountryData(){
        countryRepository.getCountryData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ response -> handleResponse(response)}, { t -> onFailure(t)})?.let { compositeDisposable.add(it) }
    }

    private fun handleResponse(countryData: Country?) {
        countryData?.let {
            Log.d(TAG, "***Data received - $it")
            countryLivedata.postValue(it)
        }

    }

    private fun onFailure(t: Throwable) {
        t.message?.let { Log.d(TAG, it)
            countryLivedata.postValue(Country("", listOf()))}
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}