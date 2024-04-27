package com.example.uts.data.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FindUsers {
    @SerializedName("items")
    private List<UserResponse> users;

    public List<UserResponse> getUsers() {
        return users;
    }
}
