package dante.uthermosillo.calculadorasism

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerFrom: Spinner = findViewById(R.id.spinner_from)
        val spinnerTo: Spinner = findViewById(R.id.spinner_to)
        val editTextValue: EditText = findViewById(R.id.et_value)
        val buttonConvert: Button = findViewById(R.id.btn_convert)
        val textViewResult: TextView = findViewById(R.id.tv_result)

        val conversionRates = mapOf(
            getString(R.string.Litro) to 1.0,
            getString(R.string.Mililitros) to 1000.0,
            getString(R.string.Galones) to 0.264172,
            getString(R.string.Onzas_Liquidas) to 33.814
        )

        val unitKeys = conversionRates.keys.toList()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            unitKeys
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        textViewResult.text = getString(R.string.default_result)

        buttonConvert.setOnClickListener {
            val fromUnit = spinnerFrom.selectedItem.toString()
            val toUnit = spinnerTo.selectedItem.toString()
            val value = editTextValue.text.toString().toDoubleOrNull()

            if (value != null) {
                val fromRate = conversionRates[fromUnit] ?: 1.0
                val toRate = conversionRates[toUnit] ?: 1.0

                val result = (value / fromRate) * toRate

                textViewResult.text = getString(R.string.resu, result, toUnit)
            } else {
                Toast.makeText(this, getString(R.string.warn), Toast.LENGTH_SHORT).show()
            }
        }}}



