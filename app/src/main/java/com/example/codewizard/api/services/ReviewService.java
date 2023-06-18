package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.singleton.CurrentUser;

import java.util.concurrent.CompletableFuture;

public class ReviewService {
    private static final String CREDENTIALS = CurrentUser.getInstance().getToken();
    private static final String AUTH_METHOD = "Bearer";

    /**
     *
     * @return ApiResponse object
     */
    public static ApiResponse reportedReviews(){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/reviews/reported",
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
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse bookReviews(int idLibro){
        String endpoint = "api/reviews/book" + "/" + idLibro;
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
     * @param resenia
     * @return ApiResponse object
     */
    public static ApiResponse leaveReview(Resenia resenia){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/reviews/leavereview",
                HttpMethod.PUT,
                AUTH_METHOD,
                CREDENTIALS,
                resenia
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
     * @param idResenia
     * @return ApiResponse object
     */
    public static ApiResponse deleteReview(int idResenia){
        Resenia resenia = new Resenia();
        resenia.setIdResenia(idResenia);
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/reviews/deletereview",
                HttpMethod.DELETE,
                AUTH_METHOD,
                CREDENTIALS,
                resenia
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
     * @param idUsuario
     * @param idResenia
     * @return ApiResponse object
     */
    public static ApiResponse reportReview(int idUsuario, int idResenia){
        Resenia resenia = new Resenia();
        resenia.setIdResenia(idResenia);
        resenia.setIdUsuario(idUsuario);
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/reviews/reportreview",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                resenia
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