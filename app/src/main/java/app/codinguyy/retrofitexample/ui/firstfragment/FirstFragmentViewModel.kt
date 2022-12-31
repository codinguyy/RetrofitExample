package app.codinguyy.retrofitexample.ui.firstfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.codinguyy.retrofitexample.data.model.Jokes
import app.codinguyy.retrofitexample.repository.Repository
import app.codinguyy.retrofitexample.util.Resource
import io.reactivex.Observable
import io.reactivex.Single

class FirstFragmentViewModel(private val repository: Repository) : ViewModel() {
    val jokesMutableLiveData: MutableLiveData<Resource> = MutableLiveData()
    val jokesLiveData: LiveData<Resource> = jokesMutableLiveData

    /**
     * Retrofit Client with CoroutineLiveData
     */

    fun getRandomJokeCoroutineLiveData(): LiveData<Resource> {
        return repository.getRandomJokeViaCoroutineLiveData()
    }

    /**
     * Retrofit Client with Callbacks and LiveData
     * In this way firebase works
     */

    fun getRandomRokeLiveData() {
        repository.getRandomJokeViaCallbacks(jokesMutableLiveData)
    }

    /**
     * RxKotlin
     */

    fun getRandomJokeViaRxKotlinSingle(): Single<Any> {
        return repository.getRandomJokeViaRxKotlinSingle()
    }

    fun getRandomJokeViaRxKotlinObservable(): Observable<Jokes> {
        return repository.getRandomJokeViaRxKotlinObservable()
    }
}
