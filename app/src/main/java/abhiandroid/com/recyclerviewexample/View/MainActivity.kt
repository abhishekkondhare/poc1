package abhiandroid.com.recyclerviewexample.View

import abhiandroid.com.recyclerviewexample.Adapter.CustomAdapter
import abhiandroid.com.recyclerviewexample.Model.Country
import abhiandroid.com.recyclerviewexample.R
import abhiandroid.com.recyclerviewexample.Utilities.Utils
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var countryObj: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryObj = Utils.parseJsonData(applicationContext)
        val countryName = findViewById<TextView>(R.id.countryName)
        countryName.text = countryObj?.title
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        countryObj?.run {
            recyclerView.adapter = CustomAdapter(this@MainActivity, this.rows)
        }
    }
}