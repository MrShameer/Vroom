package com.example.vroom.api;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Request {
    final OkHttpClient client = new OkHttpClient();

    public String RequestPost(RequestBody requestBody, String url){
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful())
//                throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }

    public String RequestGet(String url){
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }

    public String PostHeader(RequestBody requestBody, String url, String auth){
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer "+auth)
                .addHeader("Accept", "application/json")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }


    public static Target SaveImage(final String dir, String url){
        Target target = new Target(){
            File file;
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + dir);
                        if (!file.exists()){
                            file.mkdirs();
                        }
                        file = new File(file+"/"+url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        }catch (IOException e){
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }
}
