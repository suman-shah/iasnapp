package com.project75.ioeallsubjectnotes.activities;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.project75.ioeallsubjectnotes.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckResult extends AppCompatActivity {

    private EditText etSymbolNumber;
    private Button btnCheckResult, btnShowSymbolNumbers, btnDownload;
    private TextView tvSymbolNumbers;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);

        etSymbolNumber = findViewById(R.id.etSymbolNumber);
        btnCheckResult = findViewById(R.id.btnCheckResult);
        btnShowSymbolNumbers = findViewById(R.id.btnShowSymbolNumbers);
        btnDownload = findViewById(R.id.btnDownload);
        tvSymbolNumbers = findViewById(R.id.tvSymbolNumbers);

        checkAndRequestPermissions();

        btnDownload.setOnClickListener(v -> {
            String googleDriveUrl = "https://drive.google.com/uc?export=download&id=1bFD-tY9gxi9u4ZvMb2pCCUmL6jok-8ag";  // Direct download link for the file
            downloadFileFromGoogleDrive(googleDriveUrl, "symbol_numbers.txt");
        });

        btnCheckResult.setOnClickListener(v -> checkResult());

        btnShowSymbolNumbers.setOnClickListener(v -> {
            File textFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "symbol_numbers.txt");
            List<String> symbolNumbers = extractSymbolNumbersFromTextFile(textFile);
            if (symbolNumbers.isEmpty()) {
                Toast.makeText(this, "No symbol numbers found.", Toast.LENGTH_SHORT).show();
            } else {
                displaySymbolNumbers(symbolNumbers);
            }
        });
    }

    private void downloadFileFromGoogleDrive(String url, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(fileName);
        request.setDescription("Downloading file...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
            Toast.makeText(this, "Downloading file...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to initiate download.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkResult() {
        String enteredSymbolNumber = etSymbolNumber.getText().toString().trim(); // Trim input

        // Read the file from the Downloads directory
        File textFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "symbol_numbers.txt");
        List<String> symbolNumbers = extractSymbolNumbersFromTextFile(textFile);

        // Normalize the symbol numbers for comparison
        boolean isFound = false;
        for (String symbol : symbolNumbers) {
            if (symbol.equalsIgnoreCase(enteredSymbolNumber.trim())) {  // Use equalsIgnoreCase for case-insensitive comparison
                isFound = true;
                break;
            }
        }

        if (isFound) {
            Toast.makeText(this, "Symbol number " + enteredSymbolNumber + " has passed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Symbol number " + enteredSymbolNumber + " has not passed or is not found.", Toast.LENGTH_SHORT).show();
        }
    }


    private List<String> extractSymbolNumbersFromTextFile(File textFile) {
        List<String> symbolNumbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();  // Trim each line
                if (!trimmedLine.isEmpty()) {
                    symbolNumbers.add(trimmedLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return symbolNumbers;
    }


    private void displaySymbolNumbers(List<String> symbolNumbers) {
        StringBuilder numbers = new StringBuilder();
        for (String symbol : symbolNumbers) {
            numbers.append(symbol).append("\n");
        }
        tvSymbolNumbers.setText(numbers.toString());
    }

    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions granted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions denied. Please grant permissions to access files.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
