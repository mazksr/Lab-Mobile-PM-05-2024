package com.azka.praktikum8;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DataSource {
    public static ArrayList<Notes> notes = new ArrayList<>();
    private static DatabaseHelper dbHelper;

    public static boolean addNote(Context context, String title, String description) {
        dbHelper = new DatabaseHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(new GregorianCalendar().getTime());
        String timeDescription = "Created at " + formattedDate;
        values.put("timeDescription", timeDescription);

        long newRowId = db.insert("notes", null, values);

        db.close();

        if (newRowId != -1) {
            getData(context);
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateNote(Context context, int id, String title, String description) {
        dbHelper = new DatabaseHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(new GregorianCalendar().getTime());
        String timeDescription = "Updated at " + formattedDate;
        values.put("timeDescription", timeDescription);

        long newRowId = db.update("notes", values, "id = ?", new String[]{String.valueOf(id)});

        db.close();

        if (newRowId != -1) {
            getData(context);
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeNote(Context context, int id) {
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId = db.delete("notes", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        if (newRowId != -1) {
            getData(context);
            return true;
        } else {
            return false;
        }
    }

    public static void getData(Context context) {
        notes.clear();
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("notes", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = (cursor.getString(1));
                String description = (cursor.getString(2));
                String timeDescription = (cursor.getString(3));
                notes.add(new Notes(id, title, description, timeDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    public static void searchData(Context context, String search) {
        notes.clear();
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes WHERE title LIKE '%" + search + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = (cursor.getString(1));
                String description = (cursor.getString(2));
                String timeDescription = (cursor.getString(3));
                notes.add(new Notes(id, title, description, timeDescription));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}
