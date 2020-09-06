package net.idey.skyengtest.data.repository

import net.idey.skyengtest.data.network.Api
import net.idey.skyengtest.domain.model.Meaning
import net.idey.skyengtest.domain.model.Result

class MainRepository(private val api: Api) {
    suspend fun searchWord(query: String): List<Result> {
        return api.searchWord(query).map {
            Result(it.text, it.meanings.map { meaning ->
                Meaning(meaning.translation.text, meaning.transcription, meaning.imageUrl)
            })
        }
    }
}