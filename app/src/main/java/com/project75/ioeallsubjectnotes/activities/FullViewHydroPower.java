package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewHydroPower extends AppCompatActivity {
    PDFView pdfView_HydroPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_hydro_power);
        pdfView_HydroPower =(PDFView)findViewById(R.id.pdfView_HydroPower);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_HydroPower.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_HydroPower.fromAsset("story2.pdf").load();
        }
    }
}