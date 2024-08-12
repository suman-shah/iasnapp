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
import com.project75.ioeallsubjectnotes.activities.FullViewElectiveIIAppliedPhotovoltaic;
import com.project75.ioeallsubjectnotes.activities.FullViewElectiveIIBiomedicalInstrumentation;

public class ElectiveIIAppliedPhotovoltaic extends AppCompatActivity {
    ListView listView;
    Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elective_iiapplied_photovoltaic);

        listView = findViewById(R.id.pdf_list_View_ElectiveIIAppliedPhotovoltaic);
        // Retrieve the topic name from the Intent
        String topicName = getIntent().getStringExtra("topic_name");

        // Find the TextView and set the topic name
        TextView topicTextView = findViewById(R.id.subject_topic_name);
        topicTextView.setText(topicName);

        String[] story_names = getResources().getStringArray(R.array.Stories_name_ElectiveIIAppliedPhotovoltaic);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, story_names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ElectiveIIAppliedPhotovoltaic.this, FullViewElectiveIIAppliedPhotovoltaic.class);
                intent.putExtra("key_position", position);
                startActivity(intent);


            }
        });

    }
}