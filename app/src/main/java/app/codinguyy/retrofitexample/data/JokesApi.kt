package app.codinguyy.retrofitexample.data

import app.codinguyy.retrofitexample.data.model.Jokes
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface JokesApi {

    @GET("/joke")
    suspend fun getRandomJokeCoroutineLiveData(): Jokes

    // Retrofit and Callbacks
    @GET("/joke")
    fun getRandomJokeLiveData(): Call<Jokes>

    @GET("/joke")
    fun getRandomJokeRxKotlin(): Observable<Jokes>
}
