package com.example.havagas

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
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
        if (savedInstanceState != null) {
            retrieveState(savedInstanceState)
        }
        addListeners()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString("fullNameEt", binding.fullNameEt.text.toString())
        outState.putString("mailEt", binding.mailEt.text.toString())
        outState.putBoolean("shouldJoinMailListCb", binding.shouldJoinMailListCb.isChecked)
        outState.putString("phoneEt", binding.phoneEt.text.toString())
        outState.putInt("phoneRadioGroup", binding.phoneRadioGroup.checkedRadioButtonId)
        outState.putString("cellPhoneNumberEt", binding.cellPhoneNumberEt.text.toString())
        outState.putInt("genderRadioGroup", binding.genderRadioGroup.checkedRadioButtonId)
        outState.putString("birthDateEt", binding.birthDateEt.text.toString())
        outState.putInt("educationSpinner", binding.educationSpinner.selectedItemPosition)
        outState.putString("graduationYearEt", binding.graduationYearEt.text.toString())
        outState.putString("institutionEt", binding.institutionEt.text.toString())
        outState.putString("monographyEt", binding.monographyEt.text.toString())
        outState.putString("vacancyInterest", binding.vacancyInterest.text.toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun retrieveState(savedInstanceState: Bundle) {
        binding.fullNameEt.append(
            savedInstanceState.getString(
                "fullNameEt",
                binding.fullNameEt.text.toString()
            )
        )
        binding.mailEt.append(
            savedInstanceState.getString(
                "mailEt",
                binding.mailEt.text.toString()
            )
        )
        binding.shouldJoinMailListCb.isChecked = savedInstanceState.getBoolean(
            "shouldJoinMailListCb",
            binding.shouldJoinMailListCb.isChecked
        )
        binding.phoneEt.append(
            savedInstanceState.getString(
                "phoneEt",
                binding.phoneEt.text.toString()
            )
        )
        binding.phoneRadioGroup.check(
            savedInstanceState.getInt(
                "phoneRadioGroup",
                binding.phoneRadioGroup.checkedRadioButtonId
            )
        )
        binding.cellPhoneNumberEt.append(
            savedInstanceState.getString(
                "cellPhoneNumberEt",
                binding.cellPhoneNumberEt.text.toString()
            )
        )
        binding.genderRadioGroup.check(
            savedInstanceState.getInt(
                "genderRadioGroup",
                binding.genderRadioGroup.checkedRadioButtonId
            )
        )
        binding.birthDateEt.append(
            savedInstanceState.getString(
                "birthDateEt",
                binding.birthDateEt.text.toString()
            )
        )
        binding.educationSpinner.setSelection(
            savedInstanceState.getInt(
                "educationSpinner",
                binding.educationSpinner.selectedItemPosition
            )
        )
        binding.graduationYearEt.append(
            savedInstanceState.getString(
                "graduationYearEt",
                binding.graduationYearEt.text.toString()
            )
        )
        binding.institutionEt.append(
            savedInstanceState.getString(
                "institutionEt",
                binding.institutionEt.text.toString()
            )
        )
        binding.monographyEt.append(
            savedInstanceState.getString(
                "monographyEt",
                binding.monographyEt.text.toString()
            )
        )
        binding.vacancyInterest.append(
            savedInstanceState.getString(
                "vacancyInterest",
                binding.vacancyInterest.text.toString()
            )
        )
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
                        "Fundamental", "M??dio" -> {
                            graduationYearEt.visibility = View.VISIBLE
                            institutionEt.visibility = View.GONE
                            monographyEt.visibility = View.GONE
                        }
                        "Gradua????o", "Especializa????o" -> {
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

