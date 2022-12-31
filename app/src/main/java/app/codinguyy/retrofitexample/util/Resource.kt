package app.codinguyy.retrofitexample.util

import app.codinguyy.retrofitexample.data.model.Jokes

sealed class Resource {
    class NetworkCallSuccess(val data: Jokes) : Resource()
    class NetworkCallError(val exception: Exception) : Resource()
    class NetworkCallLoading() : Resource()
}