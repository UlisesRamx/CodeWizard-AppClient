package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.singleton.CurrentUser;

import java.util.concurrent.CompletableFuture;

public class AuthorService {
    private static final String CREDENTIALS = CurrentUser.getInstance().getToken();
    private static final String AUTH_METHOD = "Bearer";

    /**
     *
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse findAutorsBook(int idLibro){
        String endpoint = "api/authors/findAutorsBook" + "/" + idLibro;
        System.out.println("CREDENTIALS: "+CREDENTIALS);
        System.out.println("AUTH_METHOD: "+AUTH_METHOD);
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                endpoint,
                HttpMethod.GET,
                AUTH_METHOD,
                CREDENTIALS
        );
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
