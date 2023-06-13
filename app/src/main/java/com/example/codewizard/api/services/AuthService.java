package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Usuario;

import java.util.concurrent.CompletableFuture;


public class AuthService {
    private static final String CREDENTIALS = "cGFudGhlcjoxMjM0NTY=";
    private static final String AUTH_METHOD = "Basic";

    private AuthService(){}

    /**
     *
     * @param usuario
     * @return ApiResponse object
     */
    public static ApiResponse login(Usuario usuario){//new Usuario("Panther","123456") Para probarlo
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest("auth/login", HttpMethod.POST, AUTH_METHOD, CREDENTIALS, usuario);
        ApiResponse apiResponse = new ApiResponse();

        try {
            apiResponse = future.join();
        } catch (Exception e) {
            apiResponse.setError(true);
            apiResponse.setMessage(e.getMessage());
        }

        return apiResponse;
    }

    /**
     *
     * @param usuario
     * @return ApiResponse object
     */
    public static ApiResponse signUp(Usuario usuario){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest("auth/signin", HttpMethod.PUT, AUTH_METHOD, CREDENTIALS, usuario);
        ApiResponse apiResponse = new ApiResponse();

        try {
            apiResponse = future.join();
        } catch (Exception e) {
            apiResponse.setError(true);
            apiResponse.setMessage(e.getMessage());
        }

        return apiResponse;
    }
}
