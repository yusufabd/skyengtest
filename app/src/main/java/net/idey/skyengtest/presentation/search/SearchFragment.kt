package net.idey.skyengtest.presentation.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_fragment.*
import net.idey.skyengtest.R
import net.idey.skyengtest.domain.model.Result
import net.idey.skyengtest.presentation.word.WordFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(), SearchResultsAdapter.Listener {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewResults.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewResults.adapter = SearchResultsAdapter(this)

        editTextSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(editTextSearch.text.trim().toString())
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        observe()
    }

    private fun observe() {
        viewModel.searchResults.observe(viewLifecycleOwner) {
            (recyclerViewResults.adapter as? SearchResultsAdapter)?.setData(it)
        }
        viewModel.emptyResult.observe(viewLifecycleOwner) { empty ->
            textEmpty.isVisible = empty
        }
        viewModel.searchProgress.observe(viewLifecycleOwner) { loading ->
            recyclerViewResults.isVisible = !loading
            progressSearch.isVisible = loading
        }
        viewModel.searchError.observe(viewLifecycleOwner) {
            textEmpty.isVisible = false
            val snackbar = Snackbar.make(coordinator, it, Snackbar.LENGTH_SHORT)
                .setText(it)
            snackbar.show()
        }
    }

    override fun onResultClick(result: Result) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container,
            WordFragment.newInstance(result),
            WordFragment::class.java.simpleName
        )?.addToBackStack(WordFragment::class.java.simpleName)?.setTransition(
            FragmentTransaction.TRANSIT_FRAGMENT_FADE
        )?.commit()
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}