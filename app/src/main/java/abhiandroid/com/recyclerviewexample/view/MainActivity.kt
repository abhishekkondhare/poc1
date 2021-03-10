package abhiandroid.com.recyclerviewexample.view

import abhiandroid.com.recyclerviewexample.adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.viewmodel.CountryViewModel
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryName: TextView
    private lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
        registerCountryDataObserver()
        getListData()
    }

    private fun registerCountryDataObserver() {
        countryViewModel.countryLivedata.observe(this, {
            countryName.text = it?.title
            it?.run {
                recyclerView.adapter = CustomAdapter(this@MainActivity, this.rows)
            }
        })
    }

    private fun initViews() {
        countryName = findViewById(R.id.countryName)
        recyclerView = findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun getListData(){
        countryViewModel.getCountryData(applicationContext)
    }

    private fun initViewModel(){
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
    }
}