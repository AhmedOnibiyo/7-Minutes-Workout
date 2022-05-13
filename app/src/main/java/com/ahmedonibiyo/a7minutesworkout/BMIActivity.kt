package com.ahmedonibiyo.a7minutesworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahmedonibiyo.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)

        if (supportActionBar != null) {
            supportActionBar?.title = "CALCULATE BMI"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->

            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUSUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }
    }

    private fun calculateUnits() {
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val weightValue: Float =
                    binding?.etMetricUnitWeight?.text.toString().toFloat()

                val heightValue: Float =
                    binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResult(bmi)
            } else {
                Toast.makeText(
                    this@BMIActivity,
                    "Please enter valid values!", Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            if (validateUSUnits()) {
                val uSWeightValue: Float = binding?.etUSUnitWeight?.text.toString().toFloat()
                val uSHeightInchValue: Float =
                    binding?.etUSUnitHeightInch?.text.toString().toFloat()
                val uSHeightFeetValue: Float =
                    binding?.etUSUnitHeightFeet?.text.toString().toFloat()

                val heightValue =
                    uSHeightInchValue + uSHeightFeetValue * 12

                val bmi = 703 * (uSWeightValue / (heightValue.times(heightValue)))

                displayBMIResult(bmi)
            } else {
                Toast.makeText(
                    this@BMIActivity,
                    "Please enter valid values!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            binding?.etUSUnitWeight?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUSUnitHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUSUnitHeightInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }

        return isValid
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUSUnitWeight?.visibility = View.INVISIBLE
        binding?.tilUSUnitHeightFeet?.visibility = View.GONE
        binding?.tilUSUnitHeightInch?.visibility = View.GONE

        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etMetricUnitHeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUSUnitWeight?.visibility = View.VISIBLE
        binding?.tilUSUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilUSUnitHeightInch?.visibility = View.VISIBLE

        binding?.etUSUnitWeight?.text!!.clear()
        binding?.etUSUnitHeightFeet?.text!!.clear()
        binding?.etUSUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself!. Eat more!"
        } else if (bmi.compareTo(15f) > 0 && (bmi.compareTo(16f) <= 0)) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself!. Eat more!"
        } else if (bmi.compareTo(16f) > 0 && (bmi.compareTo(18.5f) <= 0)) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself!. Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && (bmi.compareTo(25f) <= 0)) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations!. You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && (bmi.compareTo(30f) <= 0)) {
            bmiLabel = "Overweight"
            bmiDescription =
                "Oops! You really need to take better care of yourself!. Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && (bmi.compareTo(35f) <= 0)) {
            bmiLabel = "Obese Class I (Moderately Obese)"
            bmiDescription = "Oops! You really need to take better care of yourself!. Workout more!"
        } else if (bmi.compareTo(35f) > 0 && (bmi.compareTo(40f) <= 0)) {
            bmiLabel = "Obese Class II (Severely Obese)"
            bmiDescription = "OMG! You are in a very dangerous situation!. Act now!"
        } else {
            bmiLabel = "Obese Class III (Very Severely Obese)"
            bmiDescription = "OMG! You are in a very dangerous situation!. Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription

    }
}