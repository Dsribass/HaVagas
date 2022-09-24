package com.example.havagas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        addListeners()
    }

    private fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun addListeners() {
        addInputListeners()
        addButtonListeners()
    }

    private fun addButtonListeners() {
        binding.cleanButton.setOnClickListener {
            binding.fullNameEt.text.clear()
            binding.mailEt.text.clear()
            binding.shouldJoinMailListCb.isChecked = false
            binding.phoneEt.text.clear()
            binding.phoneRadioGroup.clearCheck()
            binding.addCellPhoneNumberRadioGroup.clearCheck()
            binding.cellPhoneNumberEt.text.clear()
            binding.cellPhoneNumberEt.visibility = View.GONE
            binding.genderRadioGroup.clearCheck()
            binding.birthDateEt.text.clear()
            binding.educationSpinner.setSelection(0)
            binding.graduationYearEt.text.clear()
            binding.graduationYearEt.visibility = View.GONE
            binding.institutionEt.text.clear()
            binding.institutionEt.visibility = View.GONE
            binding.monographyEt.text.clear()
            binding.monographyEt.visibility = View.GONE
            binding.vacancyInterest.text.clear()
        }

        binding.confirmButton.setOnClickListener {
            val form = Form(
                fullNameEt = binding.fullNameEt.text.toString(),
                mailEt = binding.mailEt.text.toString(),
                shouldJoinMailListCb = binding.shouldJoinMailListCb.isChecked.toString(),
                phoneEt = binding.phoneEt.text.toString(),
                phoneRadioGroup = getRadioGroupValue(binding.phoneRadioGroup),
                cellPhoneNumberEt = binding.cellPhoneNumberEt.text.toString(),
                genderRadioGroup = getRadioGroupValue(binding.genderRadioGroup),
                birthDateEt = binding.birthDateEt.text.toString(),
                educationSpinner = binding.educationSpinner.selectedItem.toString(),
                graduationYearEt = binding.graduationYearEt.text.toString(),
                institutionEt = binding.institutionEt.text.toString(),
                monographyEt = binding.monographyEt.text.toString(),
                vacancyInterest = binding.vacancyInterest.text.toString(),
            )

            Toast.makeText(this, form.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun getRadioGroupValue(radioGroup: RadioGroup): String {
        return try {
            val id = radioGroup.checkedRadioButtonId
            radioGroup.findViewById<RadioButton>(id).text.toString()
        } catch (_: Exception) {
            ""
        }
    }

    private fun addInputListeners() {
        binding.addCellPhoneNumberRb.setOnCheckedChangeListener { radioButton, _ ->
            if (radioButton.isChecked) {
                binding.cellPhoneNumberEt.visibility = View.VISIBLE
            }

        }

        binding.notAddCellPhoneNumberRb.setOnCheckedChangeListener { radioButton, _ ->
            if (radioButton.isChecked) {
                binding.cellPhoneNumberEt.visibility = View.GONE
            }

        }

        binding.educationSpinner.onItemSelectedListener =
            object : Activity(), AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    val education = parent.getItemAtPosition(pos).toString()
                    val graduationYearEt = binding.graduationYearEt
                    val institutionEt = binding.institutionEt
                    val monographyEt = binding.monographyEt

                    when (education) {
                        "Fundamental", "Médio" -> {
                            graduationYearEt.visibility = View.VISIBLE
                            institutionEt.visibility = View.GONE
                            monographyEt.visibility = View.GONE
                        }
                        "Graduação", "Especialização" -> {
                            graduationYearEt.visibility = View.VISIBLE
                            institutionEt.visibility = View.VISIBLE
                            monographyEt.visibility = View.GONE
                        }
                        else -> {
                            graduationYearEt.visibility = View.VISIBLE
                            institutionEt.visibility = View.VISIBLE
                            monographyEt.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }
}

data class Form(
    val fullNameEt: String,
    val mailEt: String,
    val shouldJoinMailListCb: String,
    val phoneEt: String,
    val phoneRadioGroup: String,
    val cellPhoneNumberEt: String,
    val genderRadioGroup: String,
    val birthDateEt: String,
    val educationSpinner: String,
    val graduationYearEt: String,
    val institutionEt: String,
    val monographyEt: String,
    val vacancyInterest: String
)

