package com.ahmed.githubuser.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.githubuser.R;

public class FollowerViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivAvatar;
    public TextView tvUsername;


    public FollowerViewHolder(View view) {
        super(view);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        tvUsername = (TextView) view.findViewById(R.id.tvUsername);


    }
}