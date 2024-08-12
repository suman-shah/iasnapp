package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewTransmissionandDistributionSystemDesign extends AppCompatActivity {
    PDFView pdfView_TransmissionandDistributionSystemDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_transmissionand_distribution_system_design);
        pdfView_TransmissionandDistributionSystemDesign =(PDFView)findViewById(R.id.pdfView_TransmissionandDistributionSystemDesign);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_TransmissionandDistributionSystemDesign.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_TransmissionandDistributionSystemDesign.fromAsset("story2.pdf").load();
        }
    }
}