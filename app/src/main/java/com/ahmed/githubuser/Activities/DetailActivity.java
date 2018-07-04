package com.ahmed.githubuser.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.githubuser.Adapters.FollowerListAdapter;
import com.ahmed.githubuser.ImageDownloader.ImageRequest;
import com.ahmed.githubuser.Model.GitHubUserInfo;
import com.ahmed.githubuser.Network.DownloadCallback;
import com.ahmed.githubuser.Network.NetworkFragment;
import com.ahmed.githubuser.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DownloadCallback<NetworkFragment.NetworkTask.Result> {


    RecyclerView rvFollowers;
    TextView tvName, tvEmail;
    ImageView ivAvatar;
    ContentLoadingProgressBar progressBar;


    private final String BASE_URL = "https://api.github.com/";

    FollowerListAdapter adapterFollowers;

    ArrayList<GitHubUserInfo> mFollowers = new ArrayList<>();

    GitHubUserInfo mUser;
    private boolean mDownloading;
    private NetworkFragment mNetworkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ivAvatar = findViewById(R.id.ivAvatar);
        progressBar = findViewById(R.id.progress);

        rvFollowers = findViewById(R.id.rvFollowers);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.VERTICAL, false);

        rvFollowers.setLayoutManager(mLinearLayoutManager);
        adapterFollowers = new FollowerListAdapter(mFollowers);
        rvFollowers.setAdapter(adapterFollowers);

        mUser = getIntent().getParcelableExtra("USER");


        mNetworkFragment = NetworkFragment.getInstance(getFragmentManager(), BASE_URL);


        if (mUser != null) {
            tvName.setText(mUser.getName());
            tvEmail.setText(mUser.getEmail());

            new ImageRequest(mUser.getAvatarUrl()).loadImageViewFromNetwork(ivAvatar);
            downloadFollowesFromServer();

        }

    }

    private void downloadFollowesFromServer() {

        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.

            progressBar.setVisibility(View.VISIBLE);

            mNetworkFragment.setCallback(DetailActivity.this);
            mNetworkFragment.executeNetworkCall(mUser.getFollowersUrl());

            mDownloading = true;
        }
    }


    @Override
    public void updateFromDownload(NetworkFragment.NetworkTask.Result result) {

        progressBar.setVisibility(View.INVISIBLE);


        if (result == null)
            return;


        if (result.mException == null && result.mResultValue != null) {
            mFollowers.clear();

            try {
                JSONArray jsonArray = new JSONArray(result.mResultValue);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jUser = jsonArray.getJSONObject(i);

                    GitHubUserInfo user = new GitHubUserInfo();

                    user.setAvatarUrl(jUser.optString("avatar_url", null));
                    user.setName(jUser.optString("name", ""));
                    user.setEmail(jUser.optString("email", ""));
                    user.setLogin(jUser.optString("login", ""));

                    mFollowers.add(user);
                }

                adapterFollowers.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(DetailActivity.this, result.mException.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch (progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:

                break;
            case Progress.CONNECT_SUCCESS:

                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:

                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:

                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:

                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }
}
