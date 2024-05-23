package com.azka.praktikum8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NotesViewInterface {
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private CircleImageView addButton;
    private TextView noData;
    private EditText searchField;
    private ImageView cancel;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == 101) {
                    getAll();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        addButton = findViewById(R.id.btn_add_note);
        noData = findViewById(R.id.no_data);
        adapter = new NotesAdapter(this);
        searchField = findViewById(R.id.searchField);
        cancel =  findViewById(R.id.cancel);

        adapter.setNotes(DataSource.notes);
        recyclerView.setAdapter(adapter);
        getAll();

        addButton.setOnClickListener(v -> {
            resultLauncher.launch(new Intent(this, AddNotesActivity.class));
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchValue = searchField.getText().toString().trim();
                if (searchValue!=null && !searchValue.isEmpty()) {
                    cancel.setVisibility(View.VISIBLE);
                    search(searchValue);
                } else {
                    getAll();
                    cancel.setVisibility(View.GONE);
                    adapter.setNotes(DataSource.notes);
                    adapter.notifyDataSetChanged();
                }
            }
        };
        searchField.addTextChangedListener(textWatcher);
        cancel.setOnClickListener(v -> {
            getAll();
            searchField.setText(null);
            adapter.notifyDataSetChanged();
        });
    }

    public void search(String searchValue) {
        Handler handler = new Handler();
        Runnable runnable = () -> {
            handler.post(() -> {
                recyclerView.setVisibility(View.GONE);
            });
            DataSource.searchData(MainActivity.this, searchValue);
            handler.post(() -> {
                if (DataSource.notes.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                } else {
                    adapter.notifyDataSetChanged();
                    noData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }

    public void getAll() {
        Handler handler = new Handler();
        Runnable runnable = () -> {
            DataSource.getData(MainActivity.this);
            handler.post(() -> {
                adapter.notifyDataSetChanged();
                if (adapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            });
        };
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.execute(runnable);
    }


    @Override
    public void onItemClickToEdit(int position) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra("id", DataSource.notes.get(position).getId());
        intent.putExtra("pos", position);
        resultLauncher.launch(intent);
    }
}