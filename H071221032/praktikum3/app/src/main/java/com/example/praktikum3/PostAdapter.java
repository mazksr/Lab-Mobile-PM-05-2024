package com.example.praktikum3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ProfileViewInterface profileViewInterface;
    private ArrayList<Post> posts = DataSource.posts;

    public PostAdapter(ProfileViewInterface profileViewInterface) {
        this.profileViewInterface = profileViewInterface;
    }
    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.username.setText(post.getUsername());
        holder.caption.setText(post.getCaption());
        holder.pfp.setImageResource(post.getPfp());
        holder.post.setImageResource(post.getImg());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp, post;
        TextView caption, username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.post);
            pfp = itemView.findViewById(R.id.pfp);
            caption = itemView.findViewById(R.id.caption);
            username = itemView.findViewById(R.id.username);
            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profileViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            profileViewInterface.onItemClickToProfile(pos);
                        }
                    }
                }
            });
            pfp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profileViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            profileViewInterface.onItemClickToStory(pos);
                        }
                    }
                }
            });
        }
    }
}
