package abhiandroid.com.recyclerviewexample.View

import abhiandroid.com.recyclerviewexample.Adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.Model.Country
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.Utilities.Utils
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var countryObj: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the reference of RecyclerView
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        // set a LinearLayoutManager with default vertical orientation
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        countryObj = Utils.parseJsonData(applicationContext)
        countryObj?.run {
            recyclerView.adapter = CustomAdapter(this@MainActivity, this.rows)
        }
    }
}