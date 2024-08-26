package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.project75.ioeallsubjectnotes.R;

import java.util.Calendar;

public class HomeScreen extends AppCompatActivity {

        //search
        private EditText searchEditText;
        private ImageView searchIcon;
        private TextView greetingTextView;
        private WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main2);

            //greeting and date
            greetingTextView = findViewById(R.id.greetingTextView);
            webView = findViewById(R.id.webView);
            WebView myWebView = findViewById(R.id.webView);
            myWebView.setBackgroundColor(Color.TRANSPARENT);
            myWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

            // Setup WebView
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    // Optional: Check if Nepali date is correctly rendered
                    webView.evaluateJavascript(
                            "(function() { return document.getElementById('nepaliDate').innerText; })();",
                            result -> {
                                // Debugging result
                                System.out.println("Nepali Date Result: " + result);
                            }
                    );
                }
            });

            // Load HTML file from assets
            webView.loadUrl("file:///android_asset/nepali_date.html");

            // Display greeting message
            String greetingMessage = getGreetingMessage();
            greetingTextView.setText(greetingMessage);



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
        setButtonClickListener(findViewById(R.id.sendEmai), SendEmailActivity.class, R.drawable.button_pressed, R.drawable.support_icon);


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
    private String getGreetingMessage() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Good Afternoon";
        } else if (hour >= 17 && hour < 21) {
            return "Good Evening";
        } else {
            return "Good Night";
        }
    }



}
