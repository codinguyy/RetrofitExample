package app.codinguyy.retrofitexample.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import app.codinguyy.retrofitexample.data.RetrofitBuilder
import app.codinguyy.retrofitexample.data.model.Jokes
import app.codinguyy.retrofitexample.util.Resource
import app.codinguyy.retrofitexample.util.ResourceRxKotlin
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val jokesApi: RetrofitBuilder) {

    /**
     * Retrofit with RxKotlin and Observables
     */

    fun getRandomJokeViaRxKotlinObservable(): Observable<Jokes> {
        return jokesApi.provideRetrofitApiRxKotlin().getRandomJokeRxKotlin()
    }

    /**
     * Retrofit Coroutine LiveData
     */
    fun getRandomJokeViaCoroutineLiveData() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.NetworkCallLoading())
            val joke = jokesApi
                .provideRetrofitApi()
                .getRandomJokeCoroutineLiveData()
            emit(Resource.NetworkCallSuccess(joke))
        } catch (exception: Exception) {
            emit(Resource.NetworkCallError(exception))
        }
    }
    /**
     * Retrofit with Callbacks
     */
    fun getRandomJokeViaCallbacks(jokeMutableLiveData: MutableLiveData<Resource>) {
        try {
            jokeMutableLiveData.postValue(Resource.NetworkCallLoading())
            val call = jokesApi
                .provideRetrofitApi()
                .getRandomJokeLiveData()

            call.enqueue(object : Callback<Jokes> {
                override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {
                    val data = response.body()
                    data?.let {
                        jokeMutableLiveData.postValue(Resource.NetworkCallSuccess(it))
                    }
                }

                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    val exception = Exception(t)
                    jokeMutableLiveData.postValue(Resource.NetworkCallError(exception))
                }
            })
        } catch (exception: Exception) {
            jokeMutableLiveData.postValue(Resource.NetworkCallError(exception))
        }
    }


    /**
     * Retrofit with RxKotlin and Single
     */
    fun getRandomJokeViaRxKotlinSingle(): Single<Any> {
        return Single.create { emitter ->
            try {
                val joke = jokesApi.provideRetrofitApiRxKotlin().getRandomJokeRxKotlin()
                emitter.onSuccess(ResourceRxKotlin.NetworkCallSuccess(joke))
            } catch (exception: Exception) {
                emitter.onError(ResourceRxKotlin.NetworkCallError(exception))
            }
        }
    }
}
