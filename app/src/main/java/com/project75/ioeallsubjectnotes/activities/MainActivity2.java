package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.project75.ioeallsubjectnotes.R;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton notebutton;
    private ImageButton YouTubeButton;
    private ImageButton question_papers_button;
    private ImageButton syllabus_button;
    private ImageButton formulas_button;
    private ImageButton notifications_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        notebutton=findViewById(R.id.notes_button);
        notebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        YouTubeButton=findViewById(R.id.youtube_button);
        YouTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,IasnYouTubeChannel.class);
                startActivity(intent);
            }
        });

        question_papers_button=findViewById(R.id.question_papers_button);
        question_papers_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivityQuestionPaper.class);
                startActivity(intent);
            }
        });

        syllabus_button=findViewById(R.id.syllabus_button);
        syllabus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivitySyllabus.class);
                startActivity(intent);
            }
        });

        formulas_button=findViewById(R.id.formulas_button);
        formulas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivityFormula.class);
                startActivity(intent);
            }
        });

        notifications_button=findViewById(R.id.notifications_button);
        notifications_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivityNotifications.class);
                startActivity(intent);
            }
        });
        // <!-- Social Media Handles -->

        ImageButton instagramButton = findViewById(R.id.instagramButton);
        ImageButton facebookButton = findViewById(R.id.facebookButton);
        ImageButton twitterButton = findViewById(R.id.twitterButton);
        ImageButton socialyoutubeButton = findViewById(R.id.socialyoutubeButton);

        instagramButton.setOnClickListener(view -> openSocialMediaLink("https://www.instagram.com/ioeallsubjectnote"));
        facebookButton.setOnClickListener(view -> openSocialMediaLink("https://www.facebook.com/ioeallsubjectnote"));
        twitterButton.setOnClickListener(view -> openSocialMediaLink("https://x.com/iasn"));
        socialyoutubeButton.setOnClickListener(view -> openSocialMediaLink("https://www.youtube.com/@ioeallsubjectnote"));
    }

    private void openSocialMediaLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);



    }
}