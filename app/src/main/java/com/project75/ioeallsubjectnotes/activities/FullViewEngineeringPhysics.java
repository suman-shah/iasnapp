package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewEngineeringPhysics extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView_engineering_physics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_engineering_physics);

        pdfView_engineering_physics =(PDFView)findViewById(R.id.pdfView_engineering_physics);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_engineering_physics.fromAsset("story1.pdf").load();
        }
        else if (story_position==1){
            pdfView_engineering_physics.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView_engineering_physics.fromAsset("story3.pdf").load();
        }

    }
}