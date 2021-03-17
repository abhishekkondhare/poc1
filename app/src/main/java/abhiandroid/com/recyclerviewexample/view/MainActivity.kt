@file:Suppress("DEPRECATION")

package abhiandroid.com.recyclerviewexample.view

import abhiandroid.com.recyclerviewexample.BR
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.databinding.ActivityMainBinding
import abhiandroid.com.recyclerviewexample.viewmodel.CountryViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
        registerCountryDataObserver()
        showLoadingSign()
        getListData()
    }

    private fun registerCountryDataObserver() {
        countryViewModel.countryLivedata.observe(this, {
            progressBar_cyclic.visibility = View.GONE
            binding.setVariable(BR.country, it)
            binding.setVariable(BR.adapter, CustomAdapter(R.layout.rowlayout, it.rows))
            binding.executePendingBindings()
        })
    }

    private fun showLoadingSign() {
        progressBar_cyclic.visibility = View.VISIBLE
    }

    private fun getListData() {
        countryViewModel.getCountryData()
    }


    private fun initViewModel() {
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
    }
}