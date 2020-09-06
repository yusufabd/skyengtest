package net.idey.skyengtest.domain.interactor

import net.idey.skyengtest.data.repository.MainRepository
import net.idey.skyengtest.domain.model.Result

class MainInteractor(private val repository: MainRepository) {

    suspend fun searchWord(query: String): List<Result> {
        return repository.searchWord(query)
    }

}