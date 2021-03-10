package abhiandroid.com.recyclerviewexample.utilities

import abhiandroid.com.recyclerviewexample.model.Country
import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


class Utils {

    companion object{
        private fun getJsonFromAssets(context: Context): String? {
            val jsonString: String
            jsonString = try {
                val inputStream: InputStream = context.assets.open("Sample.json")
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
            return try {
                val jsonString = getJsonFromAssets(context)
                Gson().fromJson(jsonString, Country::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }


}