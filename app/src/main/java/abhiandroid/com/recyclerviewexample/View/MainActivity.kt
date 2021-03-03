package abhiandroid.com.recyclerviewexample.View

import abhiandroid.com.recyclerviewexample.Adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.Model.Country
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.Utilities.Utils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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