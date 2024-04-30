package com.example.praktikum6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private ApiService apiService;
    private ProgressBar loading;
    private ImageView pfp;
    private TextView name;
    private TextView email;
    private Group mainContent;
    private FragmentManager fragmentManager;
    private ErrorFragment errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        loading = findViewById(R.id.loading);
        pfp = findViewById(R.id.pfp);
        name = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        mainContent = findViewById(R.id.main_content);

        fragmentManager = getSupportFragmentManager();
        errorFragment = new ErrorFragment();

        int userID = getIntent().getIntExtra("id", 1);

        fragmentManager = getSupportFragmentManager();
        errorFragment = new ErrorFragment();

        Handler handler = new Handler();
        Runnable runnable = () -> {
            handler.post(() -> {
                mainContent.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
            });

            Call<UserResponse2> call = apiService.getUserById(userID);

            call.enqueue(new Callback<UserResponse2>() {
                @Override
                public void onResponse(Call<UserResponse2> call, Response<UserResponse2> response) {
                    if (response.isSuccessful()) {
                        User user = response.body().getData();

                        handler.post(() -> {
                            Picasso.get().load(user.getAvatar()).into(pfp);
                            name.setText(user.getFirst_name()+" "+user.getLast_name());
                            email.setText(user.getEmail());

                            mainContent.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);
                        });
                    } else {
//                        System.out.println(response);
                        showErrorFragment();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse2> call, Throwable t) {
//                    System.out.println(t);
                    showErrorFragment();
                }
            });
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(runnable);

    }

    private void showErrorFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.screen, errorFragment)
                .commit();
    }
}