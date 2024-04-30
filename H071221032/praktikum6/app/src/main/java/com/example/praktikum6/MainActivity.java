package com.example.praktikum6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DetailsViewInterface{

    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private AppCompatButton showMore;
    private ProgressBar loading, loading2;
    private Group mainContent;
    private List<User> users;
    private FragmentManager fragmentManager;
    private ErrorFragment errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);
        showMore = findViewById(R.id.show_more);
        loading = findViewById(R.id.loading);
        loading2 = findViewById(R.id.loading2);
        mainContent = findViewById(R.id.main_content);

        fragmentManager = getSupportFragmentManager();
        errorFragment = new ErrorFragment();

        Handler handler = new Handler();
        Runnable runnable = () -> {
            handler.post(() -> {
                mainContent.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
            });

            Call<UserResponse> call = apiService.getUsers(1, 6);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        users = response.body().getData();
                        adapter = new UserAdapter(users, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                        handler.post(() -> {
                            mainContent.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);
                        });
                    } else {
//                        System.out.println(response);
                        showErrorFragment();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
//                    System.out.println(t);
                    showErrorFragment();
                }
            });
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(runnable);

        Runnable runnable2 = () -> {
            handler.post(() -> {
                showMore.setVisibility(View.GONE);
                loading2.setVisibility(View.VISIBLE);
            });

            Call<UserResponse> call = apiService.getUsers(2, 6);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        users.addAll(response.body().getData());
                        adapter = new UserAdapter(users, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                        handler.post(() -> loading2.setVisibility(View.GONE));
                    } else {
//                        System.out.println(response);
                        showErrorFragment();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
//                    System.out.println(t);
                    showErrorFragment();
                }
            });
        };

        showMore.setOnClickListener(v -> {
            executorService.execute(runnable2);
        });
    }

    private void showErrorFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.screen, errorFragment)
                .commit();
    }

    @Override
    public void onItemClickToProfile(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("id", users.get(position).getId());
        startActivity(intent);
    }
}