package ru.graphorismo.cardwatcher.ui.history

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.graphorismo.cardwatcher.R
import ru.graphorismo.cardwatcher.ui.main.MainActivity


@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private val viewModel: HistoryViewModel by viewModels()

    lateinit var buttonBack : Button
    lateinit var buttonClear: Button
    lateinit var recyclerViewHistory: RecyclerView
    lateinit var recyclerViewHistoryAdapter: HistoryRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initUi()
        observeUiStateChanges()
        viewModel.onEvent(HistoryUiEvent.LoadActivity)
    }

    private fun initUi() {
        buttonBack = findViewById(R.id.historyActivity_button_back)
        buttonClear = findViewById(R.id.historyActivity_button_clear)
        recyclerViewHistory = findViewById(R.id.historyActivity_recyclerView_history)

        recyclerViewHistory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewHistoryAdapter = HistoryRecyclerViewAdapter(viewModel)
        recyclerViewHistory.adapter = recyclerViewHistoryAdapter

        buttonClear.setOnClickListener { viewModel.onEvent(HistoryUiEvent.ClickClearButton) }
        buttonBack.setOnClickListener { viewModel.onEvent(HistoryUiEvent.ClickBackButton) }
    }

    private fun observeUiStateChanges() {
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    if(it.lineCallback == null) {
                        recyclerViewHistoryAdapter.items = it.lines
                        recyclerViewHistoryAdapter.notifyDataSetChanged()
                    }else {
                        val intent = Intent(this@HistoryActivity,
                            MainActivity::class.java)
                        intent.putExtra("BIN",it.lineCallback)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}