package abhiandroid.com.recyclerviewexample.utilities

import abhiandroid.com.recyclerviewexample.application.App
import abhiandroid.com.recyclerviewexample.model.Country
import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


class Utils {
    companion object{
        const val BASE_URL = "https://dl.dropboxusercontent.com"

        fun getJsonFromAssets(): String? {
            val jsonString: String?
            jsonString = try {
                val inputStream: InputStream? = App.context?.let { it.assets.open("Sample.json") }
                val size: Int? = inputStream?.available()
                val buffer = size?.let { ByteArray(it) }
                inputStream?.let {
                    it.read(buffer)
                    it.close()
                }
                buffer?.let { String(it, Charsets.UTF_8) }
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return jsonString
        }

        fun parseJsonData(): String?{
            return try {
                val jsonString = getJsonFromAssets()
                Gson().toJson(jsonString)
            } catch (e: Exception) {
                null
            }
        }
    }

}