package com.example.praktikum4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;

public class HomeFragment extends Fragment implements ProfileViewInterface {

    private RecyclerView postView;
    private PostAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        if (DataSource.accounts==null) {
//            DataSource.accounts = DataSource.generateDummyAccounts();
//        }
//
//        adapter = new PostAdapter(this);
//
//        postView = view.findViewById(R.id.postRecyclerView);
//        postView.setAdapter(adapter);
//
//        Bundle bundle = getArguments();
//        if (bundle!=null) {
//            boolean newPost = bundle.getBoolean("newPost");
//            if (newPost) {
//                postView.smoothScrollToPosition(0);
//                ((MainActivity) getActivity()).setBundle(null);
//            }
//        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (DataSource.accounts==null) {
            DataSource.accounts = DataSource.generateDummyAccounts();
        }

        adapter = new PostAdapter(this);

        postView = view.findViewById(R.id.postRecyclerView);
        postView.setAdapter(adapter);

        Bundle bundle = getArguments();
        if (bundle!=null) {
            boolean newPost = bundle.getBoolean("newPost");
            if (newPost) {
                postView.smoothScrollToPosition(0);
                ((MainActivity) getActivity()).setBundle(null);
            }
        }
    }

    @Override
    public void onItemClickToProfile(int position) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("objek_account", DataSource.accounts.get(DataSource.allPost.get(position).getAccountID()));
        startActivity(intent);
    }

    @Override
    public void deleteButtonAction(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Apakah anda yakin?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    DataSource.allPost.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}