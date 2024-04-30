package com.example.praktikum5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ProfileViewInterface profileViewInterface;
    private ArrayList<Post> posts = DataSource.allPost;

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
        Post post1 = posts.get(position);

        holder.username.setText(DataSource.accounts.get(post1.getAccountID()).getUsername());
        holder.caption.setText(post1.getCaption());
        holder.pfp.setImageResource(DataSource.accounts.get(post1.getAccountID()).getPfp());
        if (post1.getImg()!=null) {
            holder.post.setImageResource(post1.getImg());
        } else {
            holder.post.setImageURI(post1.getImgUri());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        String formattedDate = sdf.format(post1.getPostedDate().getTime());
        holder.postedText.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp, post;
        TextView caption, username, postedText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.post);
            pfp = itemView.findViewById(R.id.pfp);
            caption = itemView.findViewById(R.id.caption);
            username = itemView.findViewById(R.id.username);
            postedText = itemView.findViewById(R.id.postedText);
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
        }
    }
}
