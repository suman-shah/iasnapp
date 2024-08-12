package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectromagnetic extends AppCompatActivity {
    PDFView pdfView_Electromagnetic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_electromagnetic);
        pdfView_Electromagnetic =(PDFView)findViewById(R.id.pdfView_Electromagnetic);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_Electromagnetic.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_Electromagnetic.fromAsset("story2.pdf").load();
        }
    }
}