package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Doing the onclick event
        val btn=findViewById<Button>(R.id.btnDatePicker)
        btn.setOnClickListener{view->
            clickDatePicker(view )
        }
    }
    //creating a function to do work after clicking the button
    fun clickDatePicker(view:View){
        val myCal=Calendar.getInstance()
        val year=myCal.get(Calendar.YEAR)
        val month=myCal.get(Calendar.MONTH)
        val day=myCal.get(Calendar.DAY_OF_MONTH)
        //DatePickerDialogue is the date box.
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            {view,selectedYear,selectedMonth,selectedDayOfMonth ->
                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                val tvSelectedDate=findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.setText(selectedDate)
                val simpleDateFormat=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate=simpleDateFormat.parse(selectedDate)

                val selectedDateInMinutes=theDate!!.time/60000

                val currentDate=simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                val currentDateInMinutes=currentDate!!.time/60000

                val differenceInMinutes=(currentDateInMinutes-selectedDateInMinutes)/1440

                val tvSelectedDateInMinutes=findViewById<TextView>(R.id.tvSelectedDateInMinute)
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            }

            ,year,month,day)
        //Limiting to select the next day
        dpd.datePicker.setMaxDate(Date().time-86400000)
        //Showing the total functionality
        dpd.show()
    }
}