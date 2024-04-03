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

public class StoryActivity extends AppCompatActivity{
    private ImageView pfp, img;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story);

        Post post = getIntent().getParcelableExtra("objek_post");
        Account account = getIntent().getParcelableExtra("objek_account");

        pfp = findViewById(R.id.pfp);
        img = findViewById(R.id.img);
        username = findViewById(R.id.username);

        pfp.setImageResource(post.getPfp());
        img.setImageResource(post.getStory());
        username.setText(post.getUsername());

        username.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("objek_account", account);
            intent.putExtra("objek_post", post);
            startActivity(intent);
        });
    }
}