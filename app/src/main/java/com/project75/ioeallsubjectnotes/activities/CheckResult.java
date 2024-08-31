package com.project75.ioeallsubjectnotes.activities;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    private Button btnCheckResult, btnShowSymbolNumbers, btnDownload, btnRegisterSymbol;
    private TextView tvSymbolNumbers, tvRegisteredSymbolNumber;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);

        // Initialize views
        btnCheckResult = findViewById(R.id.btnCheckResult);
        btnShowSymbolNumbers = findViewById(R.id.btnShowSymbolNumbers);
        btnDownload = findViewById(R.id.btnDownload);
        tvSymbolNumbers = findViewById(R.id.tvSymbolNumbers);
        btnRegisterSymbol = findViewById(R.id.btnRegisterSymbol);
        tvRegisteredSymbolNumber = findViewById(R.id.tvRegisteredSymbolNumber);

        // Retrieve and set the registered symbol number
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String registeredSymbolNumber = sharedPreferences.getString("registeredSymbolNumber", "");
        tvRegisteredSymbolNumber.setText("Registered Symbol Number: " + registeredSymbolNumber);

        checkAndRequestPermissions();

        btnDownload.setOnClickListener(v -> {
            String googleDriveUrl = "https://drive.google.com/uc?export=download&id=1bFD-tY9gxi9u4ZvMb2pCCUmL6jok-8ag";
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

        btnRegisterSymbol.setOnClickListener(v -> registerSymbolNumber());
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
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String registeredSymbolNumber = sharedPreferences.getString("registeredSymbolNumber", "");

        if (registeredSymbolNumber == null || registeredSymbolNumber.isEmpty()) {
            Toast.makeText(this, "No symbol number registered.", Toast.LENGTH_SHORT).show();
            return;
        }

        File textFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "symbol_numbers.txt");
        List<String> symbolNumbers = extractSymbolNumbersFromTextFile(textFile);

        boolean isFound = symbolNumbers.contains(registeredSymbolNumber);

        showAlertDialog("Result", "Symbol number " + registeredSymbolNumber + (isFound ? " has passed." : " has not passed or is not found."), isFound);
    }

    private List<String> extractSymbolNumbersFromTextFile(File textFile) {
        List<String> symbolNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
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

    private void showAlertDialog(String title, String message, boolean isSuccess) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null);

        ImageView ivDialogImage = dialogView.findViewById(R.id.ivDialogImage);
        TextView tvDialogMessage = dialogView.findViewById(R.id.tvDialogMessage);
        Button btnDialogOk = dialogView.findViewById(R.id.btnDialogOk);

        ivDialogImage.setImageResource(isSuccess ? R.drawable.success_icon : R.drawable.failure_icon);
        tvDialogMessage.setText(message);

        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        playCustomSound(isSuccess ? R.raw.success_sound : R.raw.failure_sound);

        btnDialogOk.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog.show();
    }

    private void playCustomSound(int soundResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResourceId);
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(mp -> mp.release());
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Error playing sound.", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerSymbolNumber() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_register_symbol, null);

        EditText etRegisterSymbolNumber = dialogView.findViewById(R.id.etRegisterSymbolNumber);
        Button btnRegister = dialogView.findViewById(R.id.btnRegister);
        TextView tvRegisterMessage = dialogView.findViewById(R.id.tvRegisterMessage);

        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        btnRegister.setOnClickListener(v -> {
            String enteredSymbolNumber = etRegisterSymbolNumber.getText().toString().trim();

            if (enteredSymbolNumber.isEmpty()) {
                Toast.makeText(this, "Please enter a symbol number.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("registeredSymbolNumber", enteredSymbolNumber);
                editor.apply();

                tvRegisteredSymbolNumber.setText("Registered Symbol Number: " + enteredSymbolNumber);
                Toast.makeText(this, "Symbol number registered successfully.", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
