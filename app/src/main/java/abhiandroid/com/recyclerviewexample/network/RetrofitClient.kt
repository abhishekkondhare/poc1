package abhiandroid.com.recyclerviewexample.network

import abhiandroid.com.recyclerviewexample.utilities.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient
            .Builder()
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GetCountryData::class.java)

    fun buildService(): GetCountryData {
        return retrofit
    }
}