package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class MainActivityScientificCalculator extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_scientific_calculator);
        display = findViewById(R.id.display);

        // Set up button click listeners
        setButtonListeners();
    }

    private void setButtonListeners() {
        findViewById(R.id.button_0).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_1).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_2).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_3).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_4).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_5).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_6).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_7).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_8).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_9).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_dot).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_add).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_subtract).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_multiply).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_divide).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_open_bracket).setOnClickListener(this::appendToDisplay);
        findViewById(R.id.button_close_bracket).setOnClickListener(this::appendToDisplay);

        findViewById(R.id.button_sin).setOnClickListener(v -> appendFunction("sin"));
        findViewById(R.id.button_cos).setOnClickListener(v -> appendFunction("cos"));
        findViewById(R.id.button_tan).setOnClickListener(v -> appendFunction("tan"));
        findViewById(R.id.button_log).setOnClickListener(v -> appendFunction("log"));
        findViewById(R.id.button_sqrt).setOnClickListener(v -> appendFunction("sqrt"));

        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.button_clear).setOnClickListener(v -> display.setText(""));
    }

    private void appendToDisplay(View view) {
        Button button = (Button) view;
        display.append(button.getText().toString());
    }

    private void appendFunction(String function) {
        display.append(function + "(");
    }

    private void calculateResult() {
        String expression = display.getText().toString();
        try {
            // Evaluate the expression using EvalEx library
            Expression exp = new Expression(expression);
            BigDecimal result = exp.eval();
            display.setText(result.toPlainString());
        } catch (Exception e) {
            display.setText("Error");
        }
    }
}
