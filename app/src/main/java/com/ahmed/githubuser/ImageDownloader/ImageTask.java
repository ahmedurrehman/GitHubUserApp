package com.ahmed.githubuser.ImageDownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageTask extends AsyncTask<String, Integer, ImageTask.ImageTaskResult> {


    public class ImageTaskResult {
        public Bitmap mBitmap;
        public Exception mException;

        public ImageTaskResult(Bitmap resultValue) {
            mBitmap = resultValue;
        }

        public ImageTaskResult(Exception exception) {
            mException = exception;
        }
    }


    private ImageCallback<ImageTaskResult> mCallback;

    ImageTask(ImageCallback<ImageTaskResult> callback) {
        setCallback(callback);
    }

    void setCallback(ImageCallback<ImageTaskResult> callback) {
        mCallback = callback;
    }


    /**
     * Wrapper class that serves as a union of a result value and an exception. When the download
     * task has completed, either the result value or exception can be a non-null value.
     * This allows you to pass exceptions to the UI thread that were thrown during doInBackground().
     */


    /**
     * Cancel background network operation if we do not have network connectivity.
     */
    @Override
    protected void onPreExecute() {

    }

    /**
     * Defines work to perform on the background thread.
     */
    @Override
    protected ImageTaskResult doInBackground(String... urls) {
        ImageTaskResult imageTaskResult = null;
        if (!isCancelled() && urls != null && urls.length > 0) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                Bitmap resultString = downloadBitmap(url);
                if (resultString != null) {
                    imageTaskResult = new ImageTaskResult(resultString);
                } else {
                    throw new IOException("No response received.");
                }
            } catch (Exception e) {
                imageTaskResult = new ImageTaskResult(e);
            }
        }
        return imageTaskResult;
    }

    /**
     * Updates the DownloadCallback with the imageTaskResult.
     */
    @Override
    protected void onPostExecute(ImageTaskResult imageTaskResult) {
        if (imageTaskResult != null && mCallback != null) {
            if (imageTaskResult.mException != null) {
                mCallback.updateFromDownload(imageTaskResult);
            } else if (imageTaskResult.mBitmap != null) {
                mCallback.updateFromDownload(imageTaskResult);
            }
            mCallback.finishDownloading();
        }
    }

    /**
     * Override to add special behavior for cancelled AsyncTask.
     */
    @Override
    protected void onCancelled(ImageTaskResult imageTaskResult) {
    }

    protected Bitmap downloadBitmap(URL url) throws IOException {

        Bitmap bitmap = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(stream);
//                if (stream != null) {
//                    // Converts Stream to String with max length of 500.
//                    result = AppUtils.convertStreamToString(stream);
//                }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }

}