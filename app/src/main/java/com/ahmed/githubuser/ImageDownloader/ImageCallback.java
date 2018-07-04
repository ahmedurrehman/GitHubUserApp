package com.ahmed.githubuser.ImageDownloader;

import android.net.NetworkInfo;

public interface ImageCallback<T> {

    void updateFromDownload(T result);

    void finishDownloading();
}