package com.ahmed.githubuser.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmed.githubuser.Model.GitHubUserInfo;
import com.ahmed.githubuser.Network.DownloadCallback;
import com.ahmed.githubuser.Network.NetworkFragment;
import com.ahmed.githubuser.R;
import com.ahmed.githubuser.Utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity implements DownloadCallback<NetworkFragment.NetworkTask.Result> {

    EditText etUsername;
    Button btGo;
    ContentLoadingProgressBar progressBar;
    private NetworkFragment mNetworkFragment;
    private boolean mDownloading;

    private final String BASE_URL = "https://api.github.com/";
    private String mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        etUsername = findViewById(R.id.etUsername);
        btGo = findViewById(R.id.btnGo);
        progressBar = findViewById(R.id.progress);

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                if (isValidUsername(username)) {
                    findUserFromServer(username);
                } else {
                    Toast.makeText(SearchActivity.this, "Invalid Username or Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mNetworkFragment = NetworkFragment.getInstance(getFragmentManager(), BASE_URL);

    }

    private void findUserFromServer(final String username) {


        progressBar.setVisibility(View.VISIBLE);
        mUserName = username;
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.

            mNetworkFragment.setCallback(SearchActivity.this);
            mNetworkFragment.executeNetworkCall(BASE_URL + "users/" + mUserName);
            mDownloading = true;
        }

    }

    private boolean isValidUsername(String username) {
        return (!TextUtils.isDigitsOnly(username)) || AppUtils.isValidEmail(username);
    }


    @Override
    public void updateFromDownload(NetworkFragment.NetworkTask.Result result) {


        progressBar.setVisibility(View.INVISIBLE);

        if (result == null)
            return;


        if (result.mException == null && result.mResultValue != null) {
            try {
                JSONObject jUser = new JSONObject(result.mResultValue);

                String message = jUser.optString("message", "found");
                if (message.equalsIgnoreCase("found")) {
                    // Found
                    GitHubUserInfo user = new GitHubUserInfo();

                    user.setAvatarUrl(jUser.optString("avatar_url", null));
                    user.setName(jUser.optString("name", ""));
                    user.setEmail(jUser.optString("email", ""));
                    user.setFollowersUrl(jUser.optString("followers_url", null));

                    Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);

                } else {
                    //Not Found
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(SearchActivity.this, result.mException.getMessage(), Toast.LENGTH_SHORT).show();
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
