package ru.graphorismo.cardwatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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


}