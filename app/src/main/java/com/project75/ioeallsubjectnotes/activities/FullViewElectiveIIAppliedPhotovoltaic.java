package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectiveIIAppliedPhotovoltaic extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView_ElectiveIIAppliedPhotovoltaic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_elective_iiapplied_photovoltaic);
        pdfView_ElectiveIIAppliedPhotovoltaic =(PDFView)findViewById(R.id.pdfView_ElectiveIIAppliedPhotovoltaic);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_ElectiveIIAppliedPhotovoltaic.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_ElectiveIIAppliedPhotovoltaic.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView_ElectiveIIAppliedPhotovoltaic.fromAsset("story3.pdf").load();
        }

    }
}