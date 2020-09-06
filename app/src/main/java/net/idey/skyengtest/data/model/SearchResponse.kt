package net.idey.skyengtest.data.model
import com.google.gson.annotations.SerializedName


data class SearchResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("meanings")
    val meanings: List<Meaning>
)

data class Meaning(
    @SerializedName("id")
    val id: Int,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    @SerializedName("translation")
    val translation: Translation,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("soundUrl")
    val soundUrl: String
)

data class Translation(
    @SerializedName("text")
    val text: String,
    @SerializedName("note")
    val note: String?
)