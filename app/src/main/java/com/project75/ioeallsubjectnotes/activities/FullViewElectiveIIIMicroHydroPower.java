package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectiveIIIMicroHydroPower extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView_ElectiveIIIMicroHydroPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_elective_iiimicro_hydro_power);

        pdfView_ElectiveIIIMicroHydroPower =(PDFView)findViewById(R.id.pdfView_ElectiveIIIMicroHydroPower);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_ElectiveIIIMicroHydroPower.fromAsset("ElectiveIIIMicro-HydroPower.pdf").load();
        }
        else if (story_position==1){
            pdfView_ElectiveIIIMicroHydroPower.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView_ElectiveIIIMicroHydroPower.fromAsset("story3.pdf").load();
        }

    }
}