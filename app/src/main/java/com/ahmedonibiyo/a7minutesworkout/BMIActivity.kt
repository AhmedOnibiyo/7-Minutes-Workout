package com.ahmedonibiyo.a7minutesworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahmedonibiyo.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    var binding: ActivityBmiBinding? = null

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

        binding?.btnCalculateUnits?.setOnClickListener {
            if (validateMetricUnits()) {
                val weightValue: Float =
                    binding?.etMetricUnitWeight?.text.toString().toFloat()

                val heightValue: Float =
                    binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val bmi = weightValue / (heightValue * heightValue)

                displayMetricResult(bmi)
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

    private fun displayMetricResult(bmi: Float) {
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