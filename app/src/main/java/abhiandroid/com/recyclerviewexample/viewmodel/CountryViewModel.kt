package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.BR
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.databinding.ActivityMainBinding
import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.repository.CountryRepository
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


private const val TAG = "CountryViewModel"
class CountryViewModel: ViewModel() {
    private val countryRepository = CountryRepository()

    fun getCountryData(compositeDisposable: CompositeDisposable, binding: ActivityMainBinding, progressBar_cyclic: ProgressBar){
        countryRepository.getCountryData().observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ response -> handleResponse(binding, response, progressBar_cyclic)}, { t -> onFailure(t, progressBar_cyclic) })?.let { compositeDisposable.add(it) }
    }

    private fun handleResponse(binding: ActivityMainBinding, countryData: Country?, progressBar_cyclic: ProgressBar) {
        countryData?.let {
            Log.d(TAG, "Data received - $it")
            progressBar_cyclic.visibility = View.GONE
            binding.setVariable(BR.country, it)
            binding.setVariable(BR.adapter, CustomAdapter(R.layout.rowlayout, it.rows))
            binding.executePendingBindings()
        }

    }

    private fun onFailure(t: Throwable, progressBar_cyclic: ProgressBar) {
        progressBar_cyclic.visibility = View.GONE
        t.message?.let { Log.d(TAG, it) }
    }
}