package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewFundamentalofThermodynamicsHeatTransfer extends AppCompatActivity {
    PDFView pdfView_FundamentalofThermodynamicsHeatTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_fundamentalof_thermodynamics_heat_transfer);
        pdfView_FundamentalofThermodynamicsHeatTransfer =(PDFView)findViewById(R.id.pdfView_FundamentalofThermodynamicsHeatTransfer);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_FundamentalofThermodynamicsHeatTransfer.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_FundamentalofThermodynamicsHeatTransfer.fromAsset("story2.pdf").load();
        }
    }
}