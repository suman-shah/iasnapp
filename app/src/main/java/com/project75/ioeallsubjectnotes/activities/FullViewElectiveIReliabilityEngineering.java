package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectiveIReliabilityEngineering extends AppCompatActivity {
    PDFView pdfView_ElectiveIReliabilityEngineering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_elective_ireliability_engineering);
        pdfView_ElectiveIReliabilityEngineering =(PDFView)findViewById(R.id.pdfView_ElectiveIReliabilityEngineering);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_ElectiveIReliabilityEngineering.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_ElectiveIReliabilityEngineering.fromAsset("story2.pdf").load();
        }
    }
}