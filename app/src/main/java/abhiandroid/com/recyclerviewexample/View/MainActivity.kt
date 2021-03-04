package abhiandroid.com.recyclerviewexample.View

import abhiandroid.com.recyclerviewexample.Adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.Model.Country
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.Utilities.Utils
import abhiandroid.com.recyclerviewexample.viewmodel.CountryViewModel
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        getListData()
    }

    private fun initViews() {
        countryName = findViewById<TextView>(R.id.countryName)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun getListData(){
        countryViewModel.getCountryData(applicationContext).observe(this, Observer<Country>{
            countryName.text = it?.title
            it?.run {
                recyclerView.adapter = CustomAdapter(this@MainActivity, this.rows)
            }
        })


    }

    private fun initViewModel(){
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
    }
}