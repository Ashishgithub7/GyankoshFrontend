package Video;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Notes.Note;
import com.example.practice.Notes.PDFResponse;
import com.example.practice.R;
import com.google.android.exoplayer2.ExoPlayer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoMainActivity extends AppCompatActivity {
    private WebView webView;
    private ApiService apiService;
    private String subtopic;
    private String videoUrl,midUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_main);
        subtopic = getIntent().getStringExtra("subtopic");
//        String videoUrl = "<iframe width=\"100%\" height=\"100%\" src=\""+preUrl+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        apiService = ApiClient.getClient().create(ApiService.class);

        apiService.getPdfUri(subtopic).enqueue(new Callback<PDFResponse>() {
            @Override
            public void onResponse(Call<PDFResponse> call, Response<PDFResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    midUrl = response.body().getVideoUrl();
                    Toast.makeText(VideoMainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    videoUrl = "<iframe width=\"100%\" height=\"100%\" src=\""+midUrl+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                    webView = findViewById(R.id.webView);
                    webView.loadData(videoUrl,"text/html","utf-8");
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebChromeClient(new WebChromeClient());
                } else {
                    Toast.makeText(VideoMainActivity.this, "Failed to fetch video URL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PDFResponse> call, Throwable t) {
                Toast.makeText(VideoMainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    }
