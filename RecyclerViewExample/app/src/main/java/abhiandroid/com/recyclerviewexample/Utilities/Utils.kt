package abhiandroid.com.recyclerviewexample.Utilities

import abhiandroid.com.recyclerviewexample.Model.Country
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type


class Utils {

    companion object{
        fun getJsonFromAssets(context: Context): String? {
            val jsonString: String
            jsonString = try {
                val inputStream: InputStream = context.getAssets().open("Sample.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return jsonString
        }

        fun parseJsonData(context: Context): Country?{
            try {
                val jsonString = getJsonFromAssets(context)
                return Gson().fromJson(jsonString, Country::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }


}