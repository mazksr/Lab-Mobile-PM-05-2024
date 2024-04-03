package com.example.praktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ProfileViewInterface{
    private RecyclerView postView, storyView;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        postView = findViewById(R.id.postRecyclerView);
        storyView  = findViewById(R.id.storiesRecyclerView);

        postView.setAdapter(new PostAdapter(this));
        storyView.setAdapter(new StoryAdapter(this));
    }

    @Override
    public void onItemClickToProfile(int position) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("objek_account", DataSource.accounts.get(position));
        intent.putExtra("objek_post", DataSource.posts.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemClickToStory(int position) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("objek_post", DataSource.posts.get(position));
        intent.putExtra("objek_account", DataSource.accounts.get(position));
        startActivity(intent);

    }


}