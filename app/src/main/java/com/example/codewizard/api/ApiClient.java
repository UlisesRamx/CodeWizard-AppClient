package com.example.codewizard.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClient {
    private static final String URL = "https://codewizards-api.panther14.repl.co";
    private static final String ORIGIN_HEADER = "CodeWizards";

    private ApiClient(){}

    /**
     *
     * @param endpoint
     * @param httpMethod
     * @param authMethod
     * @param credentials
     * @param arguments
     * @return CompletableFuture<ApiResponse>
     */
    public static CompletableFuture<ApiResponse> sendRequest(String endpoint, HttpMethod httpMethod, String authMethod, String credentials, Object... arguments) {
        String url = URL + "/" + endpoint;

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = null;

        if (arguments.length > 0 && httpMethod != HttpMethod.GET) {
            body = RequestBody.create(new Gson().toJson(arguments[0]), mediaType);
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .method(httpMethod.name(), body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Origin", ORIGIN_HEADER);

        if (authMethod != null && credentials != null) {
            String authorizationHeader = authMethod + " " + credentials;
            requestBuilder.header("Authorization", authorizationHeader);
        }

        Request request = requestBuilder.build();

        CompletableFuture<ApiResponse> completableFuture = new CompletableFuture<>();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setError(true);
                apiResponse.setMessage(e.getMessage());
                completableFuture.complete(apiResponse);
            }

            @Override
            public void onResponse(Call call, Response httpResponse) throws IOException {
                ApiResponse apiResponse;
                int statusCode = httpResponse.code();
                if (httpResponse.isSuccessful()) {
                    String jsonString = httpResponse.body().string();
                    apiResponse = new Gson().fromJson(jsonString, ApiResponse.class);
                } else {
                    apiResponse = new ApiResponse();
                    apiResponse.setError(true);
                }
                apiResponse.setCode(statusCode);
                completableFuture.complete(apiResponse);
            }
        });

        return completableFuture;
    }
}
