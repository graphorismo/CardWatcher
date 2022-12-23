package ru.graphorismo.cardwatcher.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.graphorismo.cardwatcher.R
import ru.graphorismo.cardwatcher.data.remote.exceptions.BlankBinException
import ru.graphorismo.cardwatcher.data.remote.exceptions.CardNotFoundException
import ru.graphorismo.cardwatcher.data.remote.exceptions.RequestTimeoutException
import ru.graphorismo.cardwatcher.domain.card.MainUiState
import ru.graphorismo.cardwatcher.ui.history.HistoryActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    lateinit var buttonSearch: Button
    lateinit var buttonHistory: Button

    lateinit var editTextBin : EditText
    lateinit var editTextSchemeNetwork : EditText
    lateinit var editTextBrand : EditText
    lateinit var editTextType : EditText
    lateinit var editTextPrepaid : EditText
    lateinit var editTextCardNumberLength : EditText
    lateinit var editTextCardNumberLuhn : EditText
    lateinit var editTextCountryName : EditText
    lateinit var editTextCountryLatitude : EditText
    lateinit var editTextCountryLongitude : EditText
    lateinit var editTextBankName : EditText
    lateinit var editTextBankURL : EditText
    lateinit var editTextBankPhone : EditText
    lateinit var editTextBankCity : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEditTextFields()
        blockInputForOutputEditTextFields()
        observeMainUiState()

        val extras = intent.extras
        if (extras != null) {
            editTextBin.setText(extras.getString("BIN"))
        }

        buttonSearch = findViewById(R.id.mainActivity_button_search)
        buttonSearch.setOnClickListener {
                viewModel.onEvent(MainUiEvent.Search(editTextBin.text.toString()))
        }

        buttonHistory = findViewById(R.id.mainActivity_button_history)
        buttonHistory.setOnClickListener {
            var intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initEditTextFields() {
        editTextBin = findViewById(R.id.mainActivity_editText_bin)
        editTextSchemeNetwork = findViewById(R.id.mainActivity_editText_schemeNetwork)
        editTextBrand = findViewById(R.id.mainActivity_editText_brand)
        editTextType = findViewById(R.id.mainActivity_editText_type)
        editTextPrepaid = findViewById(R.id.mainActivity_editText_prepaid)
        editTextCardNumberLength = findViewById(R.id.mainActivity_editText_length)
        editTextCardNumberLuhn = findViewById(R.id.mainActivity_editText_luhn)
        editTextCountryName = findViewById(R.id.mainActivity_editText_country)
        editTextCountryLatitude = findViewById(R.id.mainActivity_editText_latitude)
        editTextCountryLongitude = findViewById(R.id.mainActivity_editText_longitude)
        editTextBankName = findViewById(R.id.mainActivity_editText_bankName)
        editTextBankURL = findViewById(R.id.mainActivity_editText_bankURL)
        editTextBankPhone = findViewById(R.id.mainActivity_editText_bankPhone)
        editTextBankCity = findViewById(R.id.mainActivity_editText_bankCity)
    }

    private fun blockInputForOutputEditTextFields() {
        editTextSchemeNetwork.inputType = InputType.TYPE_NULL
        editTextBrand.inputType = InputType.TYPE_NULL
        editTextType.inputType = InputType.TYPE_NULL
        editTextPrepaid.inputType = InputType.TYPE_NULL
        editTextCardNumberLength.inputType = InputType.TYPE_NULL
        editTextCardNumberLuhn.inputType = InputType.TYPE_NULL
        editTextCountryName.inputType = InputType.TYPE_NULL
        editTextCountryLatitude.inputType = InputType.TYPE_NULL
        editTextCountryLongitude.inputType = InputType.TYPE_NULL
        editTextBankName.inputType = InputType.TYPE_NULL
        editTextBankURL.inputType = InputType.TYPE_NULL
        editTextBankPhone.inputType = InputType.TYPE_NULL
        editTextBankCity.inputType = InputType.TYPE_NULL
    }

    private fun observeMainUiState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainUiState.collect { uiState ->
                    if(uiState.exception == null){
                        updateUi(uiState)
                    }else{
                        when(uiState.exception){
                            is BlankBinException -> {
                                showErrorDialog("","Card BIN can't be blank")
                            }
                            is CardNotFoundException -> {
                                showErrorDialog("","Card not found")
                            }
                            is RequestTimeoutException -> {
                                showErrorDialog("Error","Network request timed out")
                            }
                            is Exception -> {
                                showErrorDialog("Error",uiState.exception.message.toString())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateUi(uiState: MainUiState) {
        val cardData = uiState.cardData
        editTextSchemeNetwork.setText(cardData.scheme ?:  "")
        editTextBrand.setText(cardData.brand ?:  "")
        editTextType.setText(cardData.type ?:  "")
        editTextPrepaid.setText(cardData.prepaid?.toString() ?:  "")
        editTextCardNumberLength.setText(cardData.number?.length?.toString() ?:  "")
        editTextCardNumberLuhn.setText(cardData.number?.luhn?.toString() ?:  "")
        editTextCountryName.setText(cardData.country?.name ?:  "")
        editTextCountryLatitude.setText(cardData.country?.latitude?.toString() ?:  "")
        editTextCountryLongitude.setText(cardData.country?.longitude?.toString() ?:  "")
        editTextBankName.setText(cardData.bank?.name ?:  "")
        editTextBankURL.setText(cardData.bank?.url ?:  "")
        editTextBankPhone.setText(cardData.bank?.phone ?:  "")
        editTextBankCity.setText(cardData.bank?.city ?:  "")
    }

    private fun showErrorDialog(title: String, message: String){
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setOnDismissListener {
            viewModel.onEvent(MainUiEvent.ErrorHandled)
        }
        builder.show()
    }


}