package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewCommunicationEnglish extends AppCompatActivity {
    PDFView pdfView_CommunicationEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_communication_english);
        pdfView_CommunicationEnglish =(PDFView)findViewById(R.id.pdfView_CommunicationEnglis);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_CommunicationEnglish.fromAsset("ElectiveIIBiomedicalInstrumentation.pdf").load();
        }
        else if (story_position==1){
            pdfView_CommunicationEnglish.fromAsset("story2.pdf").load();
        }
    }
}