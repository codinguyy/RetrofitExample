package app.codinguyy.retrofitexample.data

import app.codinguyy.retrofitexample.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    fun provideRetrofitApi(): JokesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .build()
            .create(JokesApi::class.java)
    }

    fun provideRetrofitApiRxKotlin(): JokesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .build()
            .create(JokesApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()
}