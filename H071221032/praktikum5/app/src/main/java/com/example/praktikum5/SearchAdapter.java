package com.example.praktikum5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ProfileViewInterface profileViewInterface;
    private ArrayList<Account> result = new ArrayList<>(1);

    public SearchAdapter(ProfileViewInterface profileViewInterface) {
        this.profileViewInterface = profileViewInterface;
    }

    public ArrayList<Account> getResult() {
        return result;
    }

    public void search(String text) {

        text = text.strip();
        if (text!=null && !text.isEmpty()) {
            for (Account account:DataSource.accounts) {
                if (account.getUsername().toLowerCase().contains(text.toLowerCase())) {
                    result.add(account);
                } else if (account.getName().toLowerCase().contains(text.toLowerCase())) {
                    result.add(account);
                }
            }
        }
    }

    public void clear() {
        result.clear();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Account account = result.get(position);

        holder.pfp.setImageResource(account.getPfp());
        holder.username.setText(account.getUsername());
        holder.name.setText(account.getName());

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pfp;
        TextView username, name;
        ConstraintLayout accountItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pfp = itemView.findViewById(R.id.pfp);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            accountItem = itemView.findViewById(R.id.userItem);
            accountItem.setOnClickListener(new View.OnClickListener() {
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
