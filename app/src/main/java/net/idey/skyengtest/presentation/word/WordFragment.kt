package net.idey.skyengtest.presentation.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.word_fragment.*
import net.idey.skyengtest.R
import net.idey.skyengtest.domain.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordFragment : Fragment() {

    private val viewModel: WordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        (requireArguments().getParcelable(ARG_RESULT) as Result?)?.let {
            toolbar.title = it.text
            val meaning = it.meanings.first()
            Glide
                .with(requireContext())
                .load("https:/${meaning.imageUrl}")
                .placeholder(R.drawable.placeholder)
                .into(imageView)

            textViewTranslation.text = meaning.translation
            textViewTranscription.text = meaning.transcription
        }
    }

    companion object {

        private const val ARG_RESULT = "result"

        fun newInstance(result: Result): WordFragment {
            val fragment = WordFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(ARG_RESULT, result)
            }
            return fragment
        }
    }

}