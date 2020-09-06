package net.idey.skyengtest.presentation.search

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.idey.skyengtest.domain.interactor.MainInteractor
import net.idey.skyengtest.domain.model.Result

class SearchViewModel(
    private val interactor: MainInteractor
) : ViewModel(), LifecycleObserver {

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val searchResults: LiveData<List<Result>>
        get() = _searchResults
    val emptyResult: LiveData<Boolean>
        get() = _emptyResult
    val searchProgress: LiveData<Boolean>
        get() = _searchProgress
    val searchError: LiveData<String>
        get() = _searchError

    private val _searchResults = MutableLiveData<List<Result>>()
    private val _emptyResult = MutableLiveData<Boolean>()
    private val _searchProgress = MutableLiveData<Boolean>()
    private val _searchError = MutableLiveData<String>()

    private val searchErrorHandler = CoroutineExceptionHandler { _, throwable ->
        _searchError.value = throwable.message
        _searchProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    fun search(query: String) {
        _emptyResult.value = false
        _searchProgress.value = true

        (uiScope + searchErrorHandler).launch {
            _searchProgress.value = false
            val results = interactor.searchWord(query)
            if (results.isEmpty()) {
                _emptyResult.value = true
            } else {
                _emptyResult.value = false
                _searchResults.value = results
            }
        }
    }

}