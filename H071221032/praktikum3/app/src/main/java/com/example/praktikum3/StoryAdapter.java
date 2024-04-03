package com.example.praktikum3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private ProfileViewInterface profileViewInterface;
    public StoryAdapter(ProfileViewInterface profileViewInterface) {
        this.profileViewInterface = profileViewInterface;
    }
    private ArrayList<Account> accounts = DataSource.accounts;
    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_item, parent, false);
        return new StoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        Account account = accounts.get(position);

        holder.username.setText(account.getUsername());
        holder.pfp.setImageResource(account.getPfp());

    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView pfp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            pfp = itemView.findViewById(R.id.pfp);
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
