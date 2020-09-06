package net.idey.skyengtest.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_result.view.*
import net.idey.skyengtest.R
import net.idey.skyengtest.domain.model.Result

class SearchResultsAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<SearchResultsAdapter.ResultHolder>() {

    private val results = ArrayList<Result>()

    fun setData(resultList: List<Result>) {
        results.clear()
        results.addAll(resultList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, null)
        return ResultHolder(view)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }

    inner class ResultHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(result: Result) {
            itemView.textResult.text = result.text
            itemView.setOnClickListener { listener.onResultClick(result) }
        }
    }

    interface Listener {
        fun onResultClick(result: Result)
    }

}