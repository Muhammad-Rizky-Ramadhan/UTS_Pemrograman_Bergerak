package com.example.uts.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uts.R;
import com.example.uts.data.response.FindUsers;
import com.example.uts.data.response.UserResponse;
import com.example.uts.data.retrofit.ApiConfig;
import com.example.uts.data.retrofit.ApiService;

import java.util.List;

import retrofit2.*;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserResponseAdapter adapter;

    private Button btnSearch;
    private EditText etSearch;

    private ApiService apiService ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);

        apiService = ApiConfig.getApiService();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString().trim();
                if(!query.isEmpty()){
                    search(query);
                } else {
                    Toast.makeText(MainActivity.this, "Please input search!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void search(String search){
        Call<FindUsers> call = apiService.findUsers(search);
        call.enqueue(new Callback<FindUsers>() {
            @Override
            public void onResponse(Call<FindUsers> call, Response<FindUsers> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UserResponse> users = response.body().getUsers();
                    adapter = new UserResponseAdapter(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FindUsers> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}