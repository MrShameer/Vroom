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

    public String Request(RequestBody requestBody, String url){

//        request = new Request.Builder()
//                .url("http://myip/task_manager/v1/register")
//                .post(requestBody)
//                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
//            String jsonData = response.body().string();
//            System.out.println(jsonData);
//            JSONObject Jobject = new JSONObject(jsonData);
//            JSONArray Jarray = Jobject.getJSONArray("employees");

//            for (int i = 0; i < responseHeaders.size(); i++) {
//                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                //return (responseHeaders.name(i) + ": " + responseHeaders.value(i));
//            }
            //System.out.println(responseHeaders.name(responseHeaders.size()-1) + "tt");
            return response.body().string();
          //  System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }
}
