package com.azka.praktikum8;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class AddNotesActivity extends AppCompatActivity {
    private EditText titleField, descField;
    private AppCompatButton button;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleField = findViewById(R.id.titleField);
        descField = findViewById(R.id.descField);
        button = findViewById(R.id.button);
        toolbar = findViewById(R.id.toolbar);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNotesActivity.this);
                builder.setMessage("Batalkan Penambahan Note?")
                        .setPositiveButton("Ya", (dialog, which) -> {
                            finish();
                        })
                        .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        toolbar.setNavigationOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Batalkan Penambahan Note?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        finish();
                    })
                    .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        button.setOnClickListener(v -> {
            String title = titleField.getText().toString();
            String desc = descField.getText().toString();

            if (title.isEmpty()) {
                titleField.setError("Title cannot be empty");
                return;
            } else if (desc.isEmpty()) {
                descField.setError("Description cannot be empty");
                return;
            }

            DataSource.addNote(this, title, desc);
            Intent resultIntent = new Intent();
            setResult(101, resultIntent);
            finish();
        });
    }


}