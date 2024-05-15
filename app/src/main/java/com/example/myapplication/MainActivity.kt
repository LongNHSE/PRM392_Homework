package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var country_list = arrayOf("Afghanistan", "Albania", "Algeria", "Andorra", "Angola")
    var items = arrayOf("1", "2", "three")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker);
        val dropDownNation: Spinner = findViewById(R.id.nation);

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, country_list.toList())

        dropDownNation.setAdapter(adapter);

        tvSelectedDate = findViewById(R.id.dob)
        btnDatePicker.setOnClickListener {
            clickDatePicker();
        }

    }

    fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            //Call back function
            { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "DatePicker works", Toast.LENGTH_LONG
                ).show()
                val selectedDate = "$dayOfMonth/${month + 1}/$year"

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                val dateFormat = sdf.parse(selectedDate);
                tvSelectedDate?.setText(selectedDate)

            },
            year,
            month,
            day
        ).show()
    }
}

