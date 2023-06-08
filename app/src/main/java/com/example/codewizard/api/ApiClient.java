package com.example.codewizard.api;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClient {
    private static final String URL = "your_api_url";

    public void doRequest(){
        try{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://codewizards-api.panther14.repl.co/api/books/findbook/Overlord")
                    .method("GET", body)
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJuYW1lIjoiUGFudGhlciJ9LCJpYXQiOjE2ODYxODAyMDEsImV4cCI6MTY4NjE4MzgwMX0.ezIn0Ze0uT8qp2ZUP2kSaKMRY6ORMs2HsPN1ajHS_iQ")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String jsonString = response.body().string();
                Gson gson = new Gson();
                ApiResponse myResponse = gson.fromJson(jsonString, ApiResponse.class);

                // Aquí puedes utilizar el objeto MyResponse
            } else {
                // Maneja el caso de una respuesta no exitosa
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // Enum for HTTP method
    public enum HttpMethod {
        GET, POST, PUT, DELETE
    }
}
