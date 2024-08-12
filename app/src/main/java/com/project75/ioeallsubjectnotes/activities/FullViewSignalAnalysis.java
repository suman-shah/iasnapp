package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewSignalAnalysis extends AppCompatActivity {
    PDFView pdfView_SignalAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_signal_analysis);
        pdfView_SignalAnalysis =(PDFView)findViewById(R.id.pdfView_SignalAnalysis);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_SignalAnalysis.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_SignalAnalysis.fromAsset("story2.pdf").load();
        }
    }
}