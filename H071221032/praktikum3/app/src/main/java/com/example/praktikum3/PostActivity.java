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

public class PostActivity extends AppCompatActivity {
    private TextView username, caption;
    private ImageView img, pfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);

        Post post = getIntent().getParcelableExtra("objek_post");
        Account account = getIntent().getParcelableExtra("objek_account");

        pfp = findViewById(R.id.pfp);
        username = findViewById(R.id.username);
        caption = findViewById(R.id.caption);
        img = findViewById(R.id.post);

        pfp.setImageResource(post.getPfp());
        username.setText(post.getUsername());
        caption.setText(post.getCaption());
        img.setImageResource(post.getImg());

        username.setOnClickListener(v -> finish());

        pfp.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("objek_post", post);
            intent.putExtra("objek_account", account);
            startActivity(intent);

        });
    }
}