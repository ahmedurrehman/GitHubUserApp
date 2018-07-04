package com.ahmed.githubuser.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.githubuser.ImageDownloader.ImageRequest;
import com.ahmed.githubuser.Model.GitHubUserInfo;
import com.ahmed.githubuser.R;
import com.ahmed.githubuser.ViewHolders.FollowerViewHolder;

import java.util.ArrayList;

/**
 * Created by ahmed.rehman on 28-Jun-18.
 */

public class FollowerListAdapter extends RecyclerView.Adapter<FollowerViewHolder> {

    ArrayList<GitHubUserInfo> followers;

    public FollowerListAdapter(ArrayList<GitHubUserInfo> users) {
        followers = users;
    }


    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_followers, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        GitHubUserInfo userInfo = followers.get(position);

        if (!userInfo.getName().isEmpty())
            holder.tvUsername.setText(userInfo.getName());
        else
            holder.tvUsername.setText(userInfo.getLogin());
        //TODO: set Image here


        new ImageRequest(userInfo.getAvatarUrl()).loadImageViewFromNetwork(holder.ivAvatar);
    }


    @Override
    public int getItemCount() {
        return followers.size();
    }
}
