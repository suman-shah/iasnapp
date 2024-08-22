package com.project75.ioeallsubjectnotes.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class MainActivityPaint extends AppCompatActivity {
    private PaintView paintView;
    private float currentBrushSize = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paint);

        paintView = findViewById(R.id.paintView);
        Button clearButton = findViewById(R.id.clearButton);
        Button redButton = findViewById(R.id.redButton);
        Button greenButton = findViewById(R.id.greenButton);
        Button blueButton = findViewById(R.id.blueButton);
        Button yellowButton = findViewById(R.id.yellowButton);
        Button eraserButton = findViewById(R.id.eraserButton);
        SeekBar brushSizeSlider = findViewById(R.id.brushSizeSlider);

        // Clear the canvas
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clearCanvas();
            }
        });

        // Change brush color to red
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.enableEraserMode(false);  // Disable eraser mode
                paintView.setBrushColor(Color.RED);
            }
        });

        // Change brush color to green
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.enableEraserMode(false);  // Disable eraser mode
                paintView.setBrushColor(Color.GREEN);
            }
        });

        // Change brush color to blue
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.enableEraserMode(false);  // Disable eraser mode
                paintView.setBrushColor(Color.BLUE);
            }
        });

        // Change brush color to yellow
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.enableEraserMode(false);  // Disable eraser mode
                paintView.setBrushColor(Color.YELLOW);
            }
        });

        // Enable eraser mode
        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.enableEraserMode(true);
            }
        });

        // Adjust brush size with slider
        brushSizeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentBrushSize = progress;
                paintView.setBrushSize(currentBrushSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
