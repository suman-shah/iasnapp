package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityIOECalander extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_ioecalander);

        // Initialize CalendarView
        CalendarView calendarView = findViewById(R.id.calendarView);

        // Optional: Set a listener for when a date is selected
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(MainActivityIOECalander.this, "Selected Date: " + date, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
