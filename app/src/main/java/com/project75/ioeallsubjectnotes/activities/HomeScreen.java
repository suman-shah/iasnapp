package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.project75.ioeallsubjectnotes.R;

public class HomeScreen extends AppCompatActivity {

        //search
        private EditText searchEditText;
        private ImageView searchIcon;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main2);

            // Initialize views
            searchEditText = findViewById(R.id.search_edit_text);
            searchIcon = findViewById(R.id.search_icon);

            // Set up search icon click listener
            searchIcon.setOnClickListener(view -> {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                    intent.putExtra("SEARCH_QUERY", query); // Pass the search query to SearchActivity
                    startActivity(intent);
                }
            });

        // Set up button click listeners using the new method
        setButtonClickListener(findViewById(R.id.notes_button), MainActivity.class, R.drawable.button_pressed,R.drawable.notes_icon);
        setButtonClickListener(findViewById(R.id.youtube_button), MainActivityYouTubeChannel.class, R.drawable.button_pressed, R.drawable.youtube_icon);
        setButtonClickListener(findViewById(R.id.question_papers_button), MainActivityQuestionPaper.class, R.drawable.button_pressed, R.drawable.question_papers_icon);
        setButtonClickListener(findViewById(R.id.syllabus_button), MainActivitySyllabus.class, R.drawable.button_pressed, R.drawable.syllabus_icon);
        setButtonClickListener(findViewById(R.id.passeddivision_button), MainActivityFormula.class, R.drawable.button_pressed, R.drawable.formulas_icon);
        setButtonClickListener(findViewById(R.id.notifications_button), MainActivityNotifications.class, R.drawable.button_pressed, R.drawable.exam_portal);
        setButtonClickListener(findViewById(R.id.IOECalander_button), MainActivityIOECalander.class, R.drawable.button_pressed, R.drawable.timetable);
        setButtonClickListener(findViewById(R.id.job_button), MainActivityJobPortal.class, R.drawable.button_pressed, R.drawable.job);
        setButtonClickListener(findViewById(R.id.Paint_button), MainActivityPaint.class, R.drawable.button_pressed, R.drawable.rattle);
        setButtonClickListener(findViewById(R.id.scientific_calculator), MainActivityScientificCalculator.class, R.drawable.button_pressed, R.drawable.polo);
        setButtonClickListener(findViewById(R.id.pdf_scanner), MainActivityPdfScan.class, R.drawable.button_pressed, R.drawable.birdie);


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

    private void setButtonClickListener(ImageButton button, Class<?> targetActivity, int pressedDrawableId, int defaultDrawableId) {
        button.setOnClickListener(view -> {
            // Change the button image to pressed state
            button.setImageDrawable(ContextCompat.getDrawable(HomeScreen.this, pressedDrawableId));

            // Navigate to the target activity
            Intent intent = new Intent(HomeScreen.this, targetActivity);
            startActivity(intent);

            // Reset the button image to the default state after a short delay
            button.postDelayed(() -> button.setImageDrawable(ContextCompat.getDrawable(HomeScreen.this, defaultDrawableId)), 200); // 200ms delay
        });
    }

    private void openSocialMediaLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
