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
// chapter 1
        topicsList.add(new Topics("Introduction 1","t11"));
        topicsList.add(new Topics("Introduction 2","t12"));
        topicsList.add(new Topics("Introduction 3","t13"));
        topicsList.add(new Topics("Introduction 4","t14"));
        chapterList.add(new Chapter("Chapter 1",topicsList));
// chapter 2
        topicsList=new ArrayList<>();
        topicsList.add(new Topics("Introduction1 1","t11"));
        topicsList.add(new Topics("Introduction1 2",""));
        topicsList.add(new Topics("Introduction1 3",""));
        topicsList.add(new Topics("Introduction1 4",""));
        chapterList.add(new Chapter("Chapter 2",topicsList));


        sendData();

    }
    void sendData(){
        customAdapter=new CustomAdapter(chapterList,MainActivity.this);
        expandableListView.setAdapter(customAdapter);
    }
}