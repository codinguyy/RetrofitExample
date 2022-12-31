package app.codinguyy.retrofitexample.util

sealed class ResourceRxKotlin {
    class NetworkCallSuccess(val data: Any) : Resource()
    class NetworkCallError(val exception: Exception) : Throwable()
    class NetworkCallLoading() : Throwable()
}
