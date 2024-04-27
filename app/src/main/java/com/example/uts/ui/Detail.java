package com.example.uts.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uts.R;
import com.example.uts.data.response.UserResponse;
import com.example.uts.data.retrofit.ApiConfig;
import com.example.uts.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView, textView2, textView3;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);

        initializeViews();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<UserResponse> userCall = apiService.getUser(username);

            showLoading(true);
            userCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        showLoading(false);
                        UserResponse user = response.body();
                        if (user != null) {
                            populateUserData(user);
                        } else {
                            showToast("Failed to get user data");
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    showToast("Error: " + t.getMessage());
                }
            });
        }
    }

    private void initializeViews() {
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.name_view);
        textView2 = findViewById(R.id.username_view);
        textView3 = findViewById(R.id.bio_view);
        imageView = findViewById(R.id.profil_picture);
    }

    private void populateUserData(UserResponse user) {
        String name = "Name: " + user.getName();
        String usernames = "Username: " + user.getLogin();
        String bio = "Bio: " + user.getBio();
        String gambar = user.getAvatarUrl();

        textView.setText(name);
        textView2.setText(usernames);
        textView3.setText(bio);
        Picasso.get().load(gambar).into(imageView);
    }

    private void showLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

