package com.example.praktikum5;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private ImageView pfp;
    private TextView username, followingValue, followersValue, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        pfp = findViewById(R.id.pfp);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        followingValue = findViewById(R.id.followingValue);
        followersValue = findViewById(R.id.followersValue);

        Account account = getIntent().getParcelableExtra("objek_account");

        pfp.setImageResource(account.getPfp());
        username.setText(account.getUsername());
        name.setText(account.getName());
        followingValue.setText(account.getFollowing().toString());
        followersValue.setText(account.getFollowers().toString());

    }
}