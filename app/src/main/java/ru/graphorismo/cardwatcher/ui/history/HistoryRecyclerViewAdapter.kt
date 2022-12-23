package ru.graphorismo.cardwatcher.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.graphorismo.cardwatcher.R

class HistoryRecyclerViewAdapter(private val historyViewModel: HistoryViewModel)
    : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {

    var items: List<String> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val line: TextView

        init {
            line = itemView.findViewById(R.id.historyItem_textView_line)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.line.text = items[position]
        holder.line.setOnClickListener {
            historyViewModel.onEvent(HistoryUiEvent.ClickHistoryLine(items[position]))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}