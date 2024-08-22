package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.project75.ioeallsubjectnotes.R;

public class MainActivityFormula extends AppCompatActivity {

    private EditText[] semesterMarks;
    private TextView[] totalMarks;
    private TextView[] percentages;
    private TextView aggregatePercentage;
    private TextView division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_formula);

        // Initialize views
        semesterMarks = new EditText[] {
                findViewById(R.id.semester1_marks),
                findViewById(R.id.semester2_marks),
                findViewById(R.id.semester3_marks),
                findViewById(R.id.semester4_marks),
                findViewById(R.id.semester5_marks),
                findViewById(R.id.semester6_marks),
                findViewById(R.id.semester7_marks),
                findViewById(R.id.semester8_marks)
        };

        totalMarks = new TextView[] {
                findViewById(R.id.total_mark_sem1),
                findViewById(R.id.total_mark_sem2),
                findViewById(R.id.total_mark_sem3),
                findViewById(R.id.total_mark_sem4),
                findViewById(R.id.total_mark_sem5),
                findViewById(R.id.total_mark_sem6),
                findViewById(R.id.total_mark_sem7),
                findViewById(R.id.total_mark_sem8)
        };

        percentages = new TextView[] {
                findViewById(R.id.percentage_sem1),
                findViewById(R.id.percentage_sem2),
                findViewById(R.id.percentage_sem3),
                findViewById(R.id.percentage_sem4),
                findViewById(R.id.percentage_sem5),
                findViewById(R.id.percentage_sem6),
                findViewById(R.id.percentage_sem7),
                findViewById(R.id.percentage_sem8)
        };

        aggregatePercentage = findViewById(R.id.aggregate_percentage);
        division = findViewById(R.id.division);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });
    }

    private void calculateResults() {
        double[] marks = new double[8];
        double[] totalMarksPerSem = {725, 650, 875, 750, 800, 725, 800, 800}; // Total marks for each semester

        double totalMarksObtainedSem1And2 = 0;
        double totalMarksObtainedSem3And4 = 0;
        double totalMarksObtainedSem5And6 = 0;
        double totalMarksObtainedSem7And8 = 0;

        // Loop through the semesters
        for (int i = 0; i < 8; i++) {
            try {
                marks[i] = Double.parseDouble(semesterMarks[i].getText().toString());
                if (marks[i] > totalMarksPerSem[i]) {
                    semesterMarks[i].setError("Marks cannot exceed total marks for the semester");
                    return;
                }
                // New condition: Check if marks are exactly equal to totalMarksPerSem[i]
                if (marks[i] == totalMarksPerSem[i]) {
                    semesterMarks[i].setError("Impossible to get full marks");
                    return;
                }


                // Update total marks obtained in different semester groups
                if (i < 2) { // Semesters 1 and 2
                    totalMarksObtainedSem1And2 += marks[i];
                } else if (i < 4) { // Semesters 3 and 4
                    totalMarksObtainedSem3And4 += marks[i];
                } else if (i < 6) { // Semesters 5 and 6
                    totalMarksObtainedSem5And6 += marks[i];
                } else { // Semesters 7 and 8
                    totalMarksObtainedSem7And8 += marks[i];
                }

                // Set total marks and percentages
                totalMarks[i].setText(String.format("%d", (int) marks[i])); // Format as integer
                percentages[i].setText(String.format("%d%%", (int) ((marks[i] / totalMarksPerSem[i]) * 100))); // Format as integer percentage

            } catch (NumberFormatException e) {
                semesterMarks[i].setError("Please enter valid marks");
                return;
            }
        }

        // Calculate aggregate percentage with specific weightages
        double aggregate =(((totalMarksObtainedSem1And2)*0.2+(totalMarksObtainedSem3And4)*0.2+(totalMarksObtainedSem5And6)*0.3+(totalMarksObtainedSem7And8)*0.3)/1537.5)*100;

        // Display aggregate percentage
        aggregatePercentage.setText(String.format("%.2f%%", aggregate));

        // Determine division based on aggregate percentage (example logic)
        if (aggregate >= 80) {
            division.setText("Distinction");
        } else if (aggregate >= 65) {
            division.setText("First Division");
        } else if (aggregate >= 50) {
            division.setText("Second Division");
        } else {
            division.setText("Fail");
        }
    }
}
