package com.project75.ioeallsubjectnotes.model;

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
import com.project75.ioeallsubjectnotes.activities.FullViewElectricMachinesII;
import com.project75.ioeallsubjectnotes.activities.FullViewHydroPower;

public class HydroPower extends AppCompatActivity {
    ListView listView;
    String[] pdfUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hydro_power);
        listView = findViewById(R.id.pdf_list_View_HydroPower);
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
                Intent intent = new Intent(HydroPower.this, FullViewHydroPower.class);
                intent.putExtra("pdf_url", pdfUrls[position]);
                startActivity(intent);


            }
        });
    }
}