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
        countryRepository.getCountryData().observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ response -> handleResponse(response)}, { t -> onFailure(t)})?.let { compositeDisposable.add(it) }
    }

    private fun handleResponse(countryData: Country?) {
        countryData?.let {
            Log.d(TAG, "***Data received - $it")
            countryLivedata.value = it
        }

    }

    private fun onFailure(t: Throwable) {
        t.message?.let { Log.d(TAG, it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}