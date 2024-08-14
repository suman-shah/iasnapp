package com.project75.ioeallsubjectnotes.model;

import android.adservices.topics.Topic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.activities.FullViewElectiveIIBiomedicalInstrumentation;
import com.project75.ioeallsubjectnotes.activities.FullViewEngineeringPhysics;

public class ElectiveIIBiomedicalInstrumentation extends AppCompatActivity {
    ListView listView;
    String[] pdfUrls;
    Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elective_iibiomedical_instrumentation);
        listView = findViewById(R.id.pdf_list_View_ElectiveIIBiomedicalInstrumentation);
        // Retrieve the topic name from the Intent
        String topicName = getIntent().getStringExtra("topic_name");

        // Find the TextView and set the topic name
        TextView topicTextView = findViewById(R.id.subject_topic_name);
        topicTextView.setText(topicName);

        // URLs of the PDFs
        pdfUrls = new String[]{
                "https://drive.google.com/drive/folders/1U_dy0KuxYdn0tee2x5wfez5kZG8pL5e2?usp=drive"

                // Add more URLs here
        };

        // PDF titles (optional, can be fetched dynamically)
        String[] pdfTitles = new String[]{
                "PDF files"

                // Corresponding titles for the PDFs

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pdfTitles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ElectiveIIBiomedicalInstrumentation.this, FullViewElectiveIIBiomedicalInstrumentation.class);
                intent.putExtra("pdf_url", pdfUrls[position]);
                startActivity(intent);


            }
        });

    }
}