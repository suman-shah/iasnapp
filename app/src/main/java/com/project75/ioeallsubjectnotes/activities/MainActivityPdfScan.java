package com.project75.ioeallsubjectnotes.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.adapters.ImageAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivityPdfScan extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_STORAGE_LOCATION = 2;
    private List<Bitmap> imageBitmapList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private EditText pdfNameEditText;
    private DocumentFile pickedDir;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pdf_scan);

        recyclerView = findViewById(R.id.recyclerView);
        pdfNameEditText = findViewById(R.id.pdfNameEditText);
        Button captureButton = findViewById(R.id.captureButton);
        Button saveButton = findViewById(R.id.saveButton);

        imageAdapter = new ImageAdapter(imageBitmapList, new ImageAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
                removeImage(position);
            }
        });
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dispatchTakePictureIntent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStorageLocation();
            }
        });

        // Request camera and storage permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Ensure the storage directory exists
        if (storageDir != null && !storageDir.exists()) {
            storageDir.mkdirs();
        }

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Save the image in full resolution
            File photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.project75.ioeallsubjectnotes.activities.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Use the full-resolution image file instead of the thumbnail
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 2; // Downscale the image to reduce memory consumption
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
            imageBitmapList.add(imageBitmap);
            imageAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_STORAGE_LOCATION && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                pickedDir = DocumentFile.fromTreeUri(this, data.getData());
                new SavePdfTask().execute();
            }
        }
    }

    private void removeImage(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Remove Image")
                .setMessage("Do you want to remove this image?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imageBitmapList.remove(position);
                        imageAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void selectStorageLocation() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, REQUEST_STORAGE_LOCATION);
    }

    private void saveAsPdf() {
        if (pickedDir == null) {
            showErrorDialog("Please select a storage location.");
            return;
        }

        String pdfName = pdfNameEditText.getText().toString().trim();
        if (pdfName.isEmpty()) {
            showErrorDialog("PDF file name cannot be empty. Please enter a valid name.");
            return;
        }

        // Sanitize file name
        pdfName = pdfName.replaceAll("[\\\\/:*?\"<>|]", "_"); // Replacing invalid characters

        DocumentFile pdfFile = pickedDir.createFile("application/pdf", pdfName);

        if (pdfFile == null) {
            showErrorDialog("Unable to create PDF file. Please check storage permissions and available space.");
            return;
        }

        try (OutputStream outputStream = getContentResolver().openOutputStream(pdfFile.getUri())) {
            if (outputStream == null) {
                showErrorDialog("Unable to access storage location.");
                return;
            }

            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Set a consistent page size for all images, e.g., A4
            PageSize pageSize = PageSize.A4;

            for (Bitmap bitmap : imageBitmapList) {
                pdfDoc.addNewPage(pageSize); // Explicitly add a new page for each image

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                // Convert the bitmap to byte array without reducing quality
                byte[] byteArray = stream.toByteArray();

                // Create Image object from byte array
                Image image = new Image(ImageDataFactory.create(byteArray));

                // Set the image to fit the page while maintaining aspect ratio
                image.setAutoScale(true);

                // Adjust image size to fit the page size
                float imageWidth = pageSize.getWidth() - document.getLeftMargin() - document.getRightMargin();
                float imageHeight = pageSize.getHeight() - document.getTopMargin() - document.getBottomMargin();
                image.scaleToFit(imageWidth, imageHeight);

                // Center the image on the page
                float x = (pageSize.getWidth() - image.getImageScaledWidth()) / 2;
                float y = (pageSize.getHeight() - image.getImageScaledHeight()) / 2;
                image.setFixedPosition(x, y);

                // Add the image to the document
                document.add(image);
            }

            document.close();
            Toast.makeText(this, "PDF saved successfully.", Toast.LENGTH_LONG).show();
        } catch (IllegalArgumentException e) {
            showErrorDialog("Invalid PDF name or path: " + e.getMessage());
        } catch (Exception e) {
            showErrorDialog("Error saving PDF: " + e.getMessage());
        }
    }




    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private class SavePdfTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                saveAsPdf();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(MainActivityPdfScan.this, "PDF saved successfully.", Toast.LENGTH_LONG).show();
            } else {
                showErrorDialog("Failed to save PDF.");
            }
        }
    }
}
