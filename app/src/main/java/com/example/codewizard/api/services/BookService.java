package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.singleton.CurrentUser;

import java.util.concurrent.CompletableFuture;

public class BookService {
    private static final String CREDENTIALS = CurrentUser.getInstance().getToken();
    private static final String AUTH_METHOD = "Bearer";

    /**
     *
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse addBook(Libro libro){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/addbook",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                libro
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

    /**
     *
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse updateBook(Libro libro){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/updatebook",
                HttpMethod.PATCH,
                AUTH_METHOD,
                CREDENTIALS,
                libro
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

    /**
     *
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse deleteBook(int idLibro){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/deletebook",
                HttpMethod.DELETE,
                AUTH_METHOD,
                CREDENTIALS,
                idLibro
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

    /**
     *
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse findbook(String libro){
        String endpoint = "api/books/findbook" + "/" + libro;
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

    /**
     *
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse detailBook(String libro){
        String endpoint = "api/books/book" + "/" + libro;
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

    /**
     *
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse suggestBook(Libro libro){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/suggest",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                libro
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
