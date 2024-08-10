package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectiveIIIWindEnergyConversionSystem extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView_ElectiveIIIWindEnergyConversionSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_elective_iiiwind_energy_conversion_system);
        pdfView_ElectiveIIIWindEnergyConversionSystem =(PDFView)findViewById(R.id.pdfView_ElectiveIIIWindEnergyConversionSystem);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_ElectiveIIIWindEnergyConversionSystem.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_ElectiveIIIWindEnergyConversionSystem.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView_ElectiveIIIWindEnergyConversionSystem.fromAsset("story3.pdf").load();
        }

    }
}