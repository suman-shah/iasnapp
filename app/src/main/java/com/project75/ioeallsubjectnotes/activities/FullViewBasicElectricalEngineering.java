package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewBasicElectricalEngineering extends AppCompatActivity {
    PDFView pdfView_BasicElectricalEngineering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_basic_electrical_engineering);
        pdfView_BasicElectricalEngineering =(PDFView)findViewById(R.id.pdfView_BasicElectricalEngineering);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_BasicElectricalEngineering.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_BasicElectricalEngineering.fromAsset("story2.pdf").load();
        }
    }
}