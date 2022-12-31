package app.codinguyy.retrofitexample.data.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Jokes(
    @SerializedName("joke")
    val joke: String
)
