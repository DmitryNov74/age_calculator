package com.example.ikalaskin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

   private var tvYear :TextView? = null
   private  var tvMonth :TextView? = null
    private var tvDay :TextView ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.btnDate)

        tvYear = findViewById(R.id.tvYear)
        tvMonth = findViewById(R.id.tvMonth)
        tvDay = findViewById(R.id.tvDay)


        btn.setOnClickListener{
            toggleButton()
        }


    }


    fun toggleButton(){
        val theCalendar = Calendar.getInstance()
        val month = theCalendar.get(Calendar.MONTH)
        val year = theCalendar.get(Calendar.YEAR)
        val day = theCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,{
            view,year,month,dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            var simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            var theDate = simpleFormat.parse(selectedDate)

            var selectedInMinutes = theDate.time / 60000

            var currentDay = simpleFormat.parse(simpleFormat.format(System.currentTimeMillis()))

            var currentDayInMinutes = currentDay.time / 60000

            var differenceInMin = currentDayInMinutes - selectedInMinutes

            var livingInYears = differenceInMin / 525600
            var livingInMonth = differenceInMin / 43200
            var livingInDays = differenceInMin / 1440

            tvYear?.text = livingInYears.toString()
            tvMonth?.text = livingInMonth.toString()
            tvDay?.text = livingInDays.toString()

        },year,month,day).show()
        Toast.makeText(this,"Valitse päivä kalenterista",Toast.LENGTH_LONG).show()
    }
}