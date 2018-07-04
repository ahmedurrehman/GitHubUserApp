package com.ahmed.githubuser.ImageDownloader;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Created by ahmed.rehman on 04-Jul-18.
 */

public class ImageRequest {


    static LruCache<String, Bitmap> imageCache;


    String imageURL;
    Bitmap bitmapImage;

    ImageTask downloadTask;

    ImageCallback<ImageTask.ImageTaskResult> downloadCallBack;


    public ImageRequest(String url) {
        imageURL = url;
    }

    public void loadImageViewFromNetwork(final ImageView imageView) {

        Bitmap bitmap = null;
        bitmap = getBitmapFromMemCache(imageURL);
        if (bitmap == null) {
            downloadCallBack = new ImageCallback<ImageTask.ImageTaskResult>() {
                @Override
                public void updateFromDownload(ImageTask.ImageTaskResult result) {

                    if (result != null) {

                        if (result.mBitmap != null) {
                            addBitmapToMemoryCache(imageURL, result.mBitmap);
                            imageView.setImageBitmap(result.mBitmap);
                        }
                    }
                }

                @Override
                public void finishDownloading() {

                }
            };

            downloadTask = new ImageTask(downloadCallBack);
            downloadTask.execute(imageURL);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }


    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            imageCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return imageCache.get(key);
    }

}
