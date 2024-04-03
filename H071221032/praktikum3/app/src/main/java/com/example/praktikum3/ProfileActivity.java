package com.example.praktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    private ImageView pfp, post;
    private TextView username, followingValue, followersValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        pfp = findViewById(R.id.pfp);
        post = findViewById(R.id.post);
        username = findViewById(R.id.username);
        followingValue = findViewById(R.id.followingValue);
        followersValue = findViewById(R.id.followersValue);

        Account account = getIntent().getParcelableExtra("objek_account");
        Post postObj = getIntent().getParcelableExtra("objek_post");

        System.out.println(account.getPfp());
        pfp.setImageResource(account.getPfp());
        post.setImageResource(postObj.getImg());
        username.setText(account.getUsername());
        followingValue.setText(account.getFollowing().toString());
        followersValue.setText(account.getFollowers().toString());

        pfp.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("objek_post", postObj);
            intent.putExtra("objek_accountt", account);
            startActivity(intent);
        });

        post.setOnClickListener(v -> {
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra("objek_post", postObj);
            intent.putExtra("objek_account", account);
            startActivity(intent);
        });
    }
}