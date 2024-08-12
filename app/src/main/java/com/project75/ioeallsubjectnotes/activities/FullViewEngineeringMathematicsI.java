package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewEngineeringMathematicsI extends AppCompatActivity {
    PDFView pdfView_EngineeringMathematicsI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_engineering_mathematics_i);
        pdfView_EngineeringMathematicsI =(PDFView)findViewById(R.id.pdfView_EngineeringMathematicsI);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_EngineeringMathematicsI.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_EngineeringMathematicsI.fromAsset("story2.pdf").load();
        }
    }
}