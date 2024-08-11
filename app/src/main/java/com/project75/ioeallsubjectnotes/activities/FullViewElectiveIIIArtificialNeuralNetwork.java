package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.project75.ioeallsubjectnotes.R;

public class FullViewElectiveIIIArtificialNeuralNetwork extends AppCompatActivity {
    String fileName;
    Intent intent;
    PDFView pdfView_ElectiveIIIArtificialNeuralNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_view_elective_iiiartificial_neural_network);
        pdfView_ElectiveIIIArtificialNeuralNetwork =(PDFView)findViewById(R.id.pdfView_ElectiveIIIArtificialNeuralNetwork);
        int story_position=getIntent().getIntExtra("key_position",0);

        if (story_position==0){
            pdfView_ElectiveIIIArtificialNeuralNetwork.fromAsset("ElectiveIIIArtificialNeuralNetwork.pdf").load();
        }
        else if (story_position==1){
            pdfView_ElectiveIIIArtificialNeuralNetwork.fromAsset("story2.pdf").load();
        }
        else if (story_position==2){
            pdfView_ElectiveIIIArtificialNeuralNetwork.fromAsset("story3.pdf").load();
        }
    }
}