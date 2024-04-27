package com.example.uts.data.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("bio")
	private String bio;

	@SerializedName("login")
	private String login;

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public String getName(){
		return name;
	}

	public String getBio(){
		return bio;
	}

	public String getLogin(){
		return login;
	}
}