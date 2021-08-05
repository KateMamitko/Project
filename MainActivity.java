package com.example.project;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project.pojo.Data;
import com.example.project.pojo.DataResponse;
import com.example.project.pojo.ObjectResponse;
import com.squareup.picasso.Picasso;

import java.util.function.Predicate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Data> data = new ArrayList<>();
    private int currentPosition = 0;
    private TextView textView;
    private ImageView imageView;
    private WebView webView;
    private Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.TextView);

        webView = findViewById(R.id.WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        imageView = findViewById(R.id.imageView);

        btnNext = findViewById(R.id.next);
        btnNext.setOnClickListener(v -> {
            requestNext();
        });

        Api api = Api.getInstance();
        ApiService apiService = api.getApiService();
        apiService.getAllIds().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    data = response.body().getData();
                    requestNext();
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Ошибка получения данных"+ t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestNext() {
        int size = data.size();
        if (!data.isEmpty()) makeIdRequest(data.get(currentPosition % size).getId());
    }

    private void makeIdRequest(long id){
        currentPosition++;
        Api api=Api.getInstance();
        ApiService apiService = api.getApiService();
        apiService.getObject(id).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                    ObjectResponse object = response.body();
                    String type = object.getType();
                    switch (type) {
                        case "text":
                            String message = object.getTile();
                            textView.setText(message);
                            textView.setVisibility(View.VISIBLE);
                            webView.setVisibility(View.GONE);
                            imageView.setVisibility(View.GONE);
                            break;
                        case "webview":
                            webView.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.GONE);
                            imageView.setVisibility(View.GONE);
                            String url = object.getUrl();
                            webView.loadUrl(url);
                            break;
                        case "image":
                            webView.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            url = object.getUrl();
                            Picasso.get().load(url).into(imageView);
                            break;
                        case "game":
                            requestNext();
                            break;
                        default:

                            break;
                    }
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error:"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}