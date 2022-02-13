package com.harshitmody72.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        //Calendar class
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        //Class give date Dialog box
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // after clicking okay
                //Month Start at 0
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/${selectedYear}"
                tvSelectDate.setText(selectedDate)

                //Simple date Format Class allow as to format the date
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                //Selected Date converted into Date Class Format
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 6000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentTimeInMinutes = currentDate.time / 6000

                        val difference = currentTimeInMinutes - selectedDateInMinutes

                        tvSelectDateInMinutes.setText("$difference")
                    }
                }
            },
            year, month, day,
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}