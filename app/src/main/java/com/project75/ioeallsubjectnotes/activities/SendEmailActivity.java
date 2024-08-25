package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;

public class SendEmailActivity extends AppCompatActivity {

    private EditText subjectEditText;
    private EditText contentEditText;
    private EditText toEmailEditText;
    private Button sendEmailButton;

    // Default email address
    private static final String DEFAULT_EMAIL = "ioeallsubjectnote@gmail.com";
    private static final String DEFAULT_SUBJECT = "Subject: Drawing 1";
    private static final String DEFAULT_CONTENT = "Year I/I | My Personal Notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        subjectEditText = findViewById(R.id.subject_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);
        toEmailEditText = findViewById(R.id.to_email_edit_text);
        sendEmailButton = findViewById(R.id.send_email_button);

        // Set the default email address
        toEmailEditText.setText(DEFAULT_EMAIL);
        subjectEditText.setText(DEFAULT_SUBJECT);
        contentEditText.setText(DEFAULT_CONTENT);

        sendEmailButton.setOnClickListener(v -> sendEmail());
    }

    private void sendEmail() {
        String toEmail = toEmailEditText.getText().toString().trim();
        String subject = subjectEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        if (TextUtils.isEmpty(toEmail)) {
            showToast("Please enter the recipient email address.");
            return;
        }

        if (TextUtils.isEmpty(subject)) {
            showToast("Please enter the subject.");
            return;
        }

        if (TextUtils.isEmpty(content)) {
            showToast("Please enter the content.");
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle case where no email client is installed
            showToast("No email client installed.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(SendEmailActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
