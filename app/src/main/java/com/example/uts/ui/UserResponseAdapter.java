package com.example.uts.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.R;
import com.example.uts.data.response.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserResponseAdapter extends RecyclerView.Adapter<UserResponseAdapter.ViewHolder> {
    private List<UserResponse> users;

    public UserResponseAdapter(List<UserResponse> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nama, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatarImageView;
        TextView usernameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.profil_picture);
            usernameTextView = itemView.findViewById(R.id.username_view);
            itemView.setOnClickListener(this);
        }

        public void bind(UserResponse user) {
            usernameTextView.setText(user.getLogin());
            Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
        }

        @Override
        public void onClick(View v) {
            UserResponse user = users.get(getAdapterPosition());
            Intent intent = new Intent(v.getContext(), Detail.class);
            intent.putExtra("nama", user.getName());
            intent.putExtra("username", user.getLogin());
            intent.putExtra("bio", user.getBio());
            intent.putExtra("gambar", user.getAvatarUrl());
            v.getContext().startActivity(intent);
        }
    }
}