package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewEngineeringDrawingI extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_engineering_drawing_i);

        pdfView=(PDFView)findViewById(R.id.pdfView);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView.fromAsset("story1.pdf").load();
        }
        else if (story_position==1){
            pdfView.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView.fromAsset("story3.pdf").load();
        }


    }
}