package net.idey.skyengtest.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.idey.skyengtest.domain.interactor.MainInteractor
import net.idey.skyengtest.domain.model.Result
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchViewModelTest {

    @Mock
    private lateinit var interactor: MainInteractor

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `search loading`() {
        runBlocking {
            launch(Dispatchers.Main) {
                val query = "query"
                val results = listOf<Result>()
                whenever(interactor.searchWord(any())).thenReturn(results)

                val viewModel = SearchViewModel(interactor)
                viewModel.search(query)

                assert(viewModel.searchProgress.value == true)
            }
        }
    }

}