package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewProjectPartB extends AppCompatActivity {
    PDFView pdfView_projectPartB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_project_part_b);
        pdfView_projectPartB =(PDFView)findViewById(R.id.pdfView_ProjectPartB);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_projectPartB.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_projectPartB.fromAsset("story2.pdf").load();
        }
    }
}