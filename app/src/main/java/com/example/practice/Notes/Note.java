package com.example.practice.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Note extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_CODE =100;
    private PDFView pdfView;
    private TextView pageInfo;
    private int totalPageCount;
    private ImageView downloadBtn;
    private String pdfUrl;

    private String pdfFileName,  subtopic;
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        subtopic = getIntent().getStringExtra("subtopic");


        pdfView = findViewById(R.id.pdfView);
        downloadBtn = findViewById(R.id.downloadBtn);
        // Display the notes for the selected subtopic
        TextView textView = findViewById(R.id.subTopic);
        textView.setText(subtopic);

        apiService = ApiClient.getClient().create(ApiService.class);
        fetchPdfUrl();
        if (isInternetAvailable()) {
            fetchPdfUrl();  // Fetch the PDF from the server if the internet is available
        } else {
            loadPdfFromLocalUri();  // Load the PDF from local storage if the internet is off
        }
        downloadBtn.setOnClickListener(view -> {
            if (pdfUrl != null) {
                checkPermissionAndDownload();
            }
        });




            // Fetch notes from the server using Retrofit or another networking library
//        pageInfo = findViewById(R.id.pageCounter);
//        pdfView = findViewById(R.id.pdfView);
//        Uri uri=Uri.parse("http://192.168.2.6:8080/api/v1/note/file/ecommerce.pdf");
//        pdfView.fromUri(uri) .onPageChange((page, pageCount) -> {
//            // Update the total page count
//            totalPageCount = pageCount;
//            // Update the current page number in the TextView
//            pageInfo.setText(String.format("Page %d of %d", page + 1, totalPageCount));
//        }).load();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    } private void fetchPdfUrl() {
        apiService.getPdfUri(subtopic).enqueue(new Callback<PDFResponse>() {
            @Override
            public void onResponse(Call<PDFResponse> call, Response<PDFResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pdfUrl = response.body().getPdfNotesUrl();
                    loadPdfFromUrl(pdfUrl);
                } else {
                    Toast.makeText(Note.this, "Failed to fetch PDF URL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PDFResponse> call, Throwable t) {
                Toast.makeText(Note.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadPdfFromUrl(String pdfUrl) {
        new Thread(() -> {
            try {
                InputStream inputStream = new java.net.URL(pdfUrl).openStream();
                runOnUiThread(() -> pdfView.fromStream(inputStream).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void checkPermissionAndDownload() {
        // No need to check permission for Android 10 and above (API 29+)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            downloadPdf();
        } else {
            // Handle storage permissions for devices running Android 9 (API level 28) and below
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            } else {
                downloadPdf();
            }
        }
    }


    private void downloadPdf() {
        apiService.downloadFileByUrl(pdfUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Uri pdfUri = savePdfFile(response.body());
                    if (pdfUri != null) {
                        // Save the URI to SharedPreferences (or a database) for later access
                        getSharedPreferences("notes_uris", MODE_PRIVATE).edit().putString("last_pdf_uri", pdfUri.toString()).apply();

                        Toast.makeText(Note.this, "PDF downloaded successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Note.this, "Failed to download PDF", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Note.this, "Download Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    boolean success = savePdfFile(response.body());
//                    if (success) {
//                        Toast.makeText(Note.this, "PDF downloaded successfully!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Note.this, "Failed to download PDF", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(Note.this, "Download Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


private Uri savePdfFile(ResponseBody body) {
    String str = subtopic.replaceAll("\\s+", "");  // Remove spaces from subtopic
    try {
        String fileName = str + ".pdf";

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        Uri uri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        }

        if (uri == null) {
            return null;
        }

        OutputStream outputStream = getContentResolver().openOutputStream(uri);
        if (outputStream == null) {
            return null;
        }

        InputStream inputStream = body.byteStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return uri;  // Return the URI of the saved file
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void loadPdfFromLocalUri() {
        String fileName = subtopic.replaceAll("\\s+", "") + ".pdf";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (file.exists()) {
            Uri pdfUri = Uri.fromFile(file);
            pdfView.fromUri(pdfUri).load();  // Load the PDF from local file
        } else {
            Toast.makeText(this, "No PDF found in local storage", Toast.LENGTH_SHORT).show();
        }
    }





@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downloadPdf();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }







}
