package com.azka.praktikum8;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Notes {
    private int id;
    private String title, description, timeDescription;


    public Notes(int id, String title, String description, String timeDescription) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timeDescription = timeDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeDescription() {
        return timeDescription;
    }

    public void setTimeDescription(String timeDescription) {
        this.timeDescription = timeDescription;
    }
}
