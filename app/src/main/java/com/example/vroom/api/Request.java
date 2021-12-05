package com.example.vroom.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Request {
    final OkHttpClient client = new OkHttpClient();

    public String RequestPost(RequestBody requestBody, String url){
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
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
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }
}
