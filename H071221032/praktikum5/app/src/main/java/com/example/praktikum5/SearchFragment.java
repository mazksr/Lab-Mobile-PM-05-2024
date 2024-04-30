package com.example.praktikum5;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements ProfileViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView searchResult;
    private SearchAdapter adapter;
    private EditText searchField;
    private ProgressBar loading;
    private ImageView cancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchResult = view.findViewById(R.id.result);
        adapter = new SearchAdapter(this);
        searchField = view.findViewById(R.id.searchField);
        loading = view.findViewById(R.id.loading);
        cancel =  view.findViewById(R.id.cancel);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchField.getText().toString()!=null && !searchField.getText().toString().isEmpty()) {
                    cancel.setVisibility(View.VISIBLE);

                    Handler handler = new Handler();
                    Runnable runnable = () -> {
                        handler.post(() -> {
                            searchResult.setVisibility(View.GONE);
                            loading.setVisibility(View.VISIBLE);
                        });
                        adapter.clear();
                        adapter.search(searchField.getText().toString());
                        handler.postDelayed(() -> {
                            loading.setVisibility(View.GONE);
                            searchResult.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }, 400);
                    };
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(runnable);
                } else {
                    cancel.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    searchResult.setVisibility(View.GONE);
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        };

        cancel.setOnClickListener(v -> {
            searchField.setText(null);
            adapter.clear();
            adapter.notifyDataSetChanged();
        });

        searchResult.setAdapter(adapter);
        searchField.addTextChangedListener(textWatcher);

        return view;
    }


    @Override
    public void onItemClickToProfile(int position) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("objek_account", DataSource.accounts.get(adapter.getResult().get(position).getObjectId()));
        startActivity(intent);
    }

}