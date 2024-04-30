package com.example.praktikum6;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }


}
