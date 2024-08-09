package com.project75.ioeallsubjectnotes.activities;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.adater.CustomAdapter;
import com.project75.ioeallsubjectnotes.model.Chapter;
import com.project75.ioeallsubjectnotes.model.Topics;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    CustomAdapter customAdapter;
    List<Chapter> chapterList;
    List<Topics> topicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        expandableListView=findViewById(R.id.expandableListView);

        addData();
        sendData();


    }
    void addData(){
        chapterList=new ArrayList<>();
        topicsList=new ArrayList<>();
// First Semester
        topicsList.add(new Topics("Engineering Drawing I","t11"));
        topicsList.add(new Topics("Engineering Physics","t12"));
        topicsList.add(new Topics("Applied Mechanics","t13"));
        topicsList.add(new Topics("Basic Electrical Engineering","t14"));
        topicsList.add(new Topics("Engineering Mathematics I","t14"));
        topicsList.add(new Topics("Computer Programming","t14"));
        chapterList.add(new Chapter("First Semester",topicsList));
// Second Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Engineering Mathematics II",""));
        topicsList.add(new Topics("Engineering Drawing II",""));
        topicsList.add(new Topics("Basic Electronics Engineering",""));
        topicsList.add(new Topics("Engineering Chemistry",""));
        topicsList.add(new Topics("Fundamental of Thermodynamics & Heat Transfer",""));
        topicsList.add(new Topics("Workshop Technology",""));
        chapterList.add(new Chapter("Second Semester",topicsList));
// Third Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Engineering Mathematics III","t11"));
        topicsList.add(new Topics("Object Oriented Programming",""));
        topicsList.add(new Topics("Electric Circuit Theory",""));
        topicsList.add(new Topics("Electronics Devices and Circuit",""));
        topicsList.add(new Topics("Digital Logic",""));
        topicsList.add(new Topics("Electrical Engineering Material",""));
        topicsList.add(new Topics("Electromagnetic",""));
        chapterList.add(new Chapter("Third Semester",topicsList));
// Fourth Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Numerical Method","t11"));
        topicsList.add(new Topics("Applied Mathematics",""));
        topicsList.add(new Topics("Instrumentation I",""));
        topicsList.add(new Topics("Microprocessor",""));
        topicsList.add(new Topics("Power System Analysis I",""));
        topicsList.add(new Topics("Electrical Machines I",""));
        chapterList.add(new Chapter("Fourth Semester",topicsList));
// Fifth Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Electric Machines - II","t11"));
        topicsList.add(new Topics("Electric Machine Design",""));
        topicsList.add(new Topics("Power System Analysis II",""));
        topicsList.add(new Topics("Communication English",""));
        topicsList.add(new Topics("Probability and Statistics",""));
        topicsList.add(new Topics("Control System",""));
        topicsList.add(new Topics("Instrumentation II",""));
        chapterList.add(new Chapter("Fifth Semester",topicsList));
// Sixth Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Engineering Economics","t11"));
        topicsList.add(new Topics("Hydro Power",""));
        topicsList.add(new Topics("Switchgear & Protection",""));
        topicsList.add(new Topics("Digital Control System",""));
        topicsList.add(new Topics("Industrial Power Distribution & Illumination",""));
        topicsList.add(new Topics("Signal Analysis",""));
        chapterList.add(new Chapter("Sixth Semester",topicsList));
// Seventh Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Project Engineering","t11"));
        topicsList.add(new Topics("Technology Environment and Society",""));
        topicsList.add(new Topics("Power Electronics",""));
        topicsList.add(new Topics("Organization and Management",""));
        topicsList.add(new Topics("Utilization of Electrical Energy",""));
        topicsList.add(new Topics("Power Plant Equipment",""));
        topicsList.add(new Topics("Project (Part A)",""));
        topicsList.add(new Topics("Elective I : Energy Electrical System Management",""));
        topicsList.add(new Topics("Elective I : Reliability Engineering",""));
        topicsList.add(new Topics("Elective I : Rural Electrification",""));
        chapterList.add(new Chapter("Seventh Semester",topicsList));
// Eighth Semester
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Engineering Professional Practice","t11"));
        topicsList.add(new Topics("High Voltage Engineering",""));
        topicsList.add(new Topics("Power Plant Design",""));
        topicsList.add(new Topics("Transmission and Distribution System Design",""));
        topicsList.add(new Topics("Project (Part B)",""));
        topicsList.add(new Topics("Elective II : Advanced Power System Analysis",""));
        topicsList.add(new Topics("Elective II : Applied Photovoltaic",""));
        topicsList.add(new Topics("Elective III : Artificial Neural Network",""));
        topicsList.add(new Topics("Elective III : Micro-Hydro Power",""));
        topicsList.add(new Topics("Elective III : Wind Energy Conversion System",""));
        topicsList.add(new Topics("Elective II : Biomedical Instrumentation",""));
        chapterList.add(new Chapter("Eighth Semester",topicsList));



        sendData();

    }
    void sendData(){
        customAdapter=new CustomAdapter(chapterList,MainActivity.this);
        expandableListView.setAdapter(customAdapter);
    }
}