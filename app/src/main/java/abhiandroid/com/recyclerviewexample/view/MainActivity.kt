@file:Suppress("DEPRECATION")

package abhiandroid.com.recyclerviewexample.view

import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.databinding.ActivityMainBinding
import abhiandroid.com.recyclerviewexample.viewmodel.CountryViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
        initViewModel()
        showLoadingSign()
        getListData()
    }

    private fun showLoadingSign() {
        progressBar_cyclic.visibility = View.VISIBLE
    }

    private fun getListData() {
        countryViewModel.getCountryData(compositeDisposable, binding, progressBar_cyclic)
    }

    private fun initViewModel() {
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}