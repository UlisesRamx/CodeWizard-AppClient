package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Libro;
import com.example.codewizard.singleton.CurrentUser;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BookService {
    private static final String CREDENTIALS = CurrentUser.getInstance().getToken();
    private static final String AUTH_METHOD = "Bearer";

    /**
     *
     * @return ApiResponse object
     */
    public static ApiResponse allBooks(){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/all",
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
    public static ApiResponse findBook(String libro){
        String endpoint = "api/books/findbook" + "/" + libro;
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

    /**
     *
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse findAutorsBook(int idLibro){
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

    /**
     *
     * @param idEstado
     * @param idUsuario
     * @return ApiResponse object
     */
    public static ApiResponse findLibrary(int  idEstado, int idUsuario){
        String endpoint = "api/books/findLibrary"+"/"+idUsuario+ "/" +idEstado;
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
     * @param idUsuario
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse findLibraryByUser(int  idUsuario, int idLibro){
        String endpoint = "api/books/findLibraryByUser"+"/"+idUsuario+ "/" +idLibro;
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
     * @param libro
     * @return ApiResponse object
     */
    public static ApiResponse registerBookLibrary(Libro libro){
        String endpoint = "api/books/registerBookLibrary";
        //String endpoint = "api/books/registerBookLibrary"+"/"+idUsuario+ "/" +idLibro+"/"+idEstado;
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                endpoint,
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
     * @param idUsuario
     * @param idLibro
     * @return ApiResponse object
     */
    public static ApiResponse deleteBookLibrary(int idUsuario, int idLibro){
        String endpoint = "api/books/deleteBookLibrary"+"/"+idUsuario+ "/" +idLibro;
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                endpoint,
                HttpMethod.DELETE,
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



    public static ApiResponse allAuthorsBook(){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/authors/AllAuthors",
                HttpMethod.GET,
                AUTH_METHOD,
                CREDENTIALS
        );
        System.out.println("CREDENTIALS: "+CREDENTIALS);
        System.out.println("AUTH_METHOD: "+AUTH_METHOD);
        ApiResponse apiResponse = new ApiResponse();

        try {
            apiResponse = future.join();
        } catch (Exception e) {
            apiResponse.setError(true);
            apiResponse.setMessage(e.getMessage());
        }

        return apiResponse;
    }

    public static ApiResponse allEditorialBooks(){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/books/allEditoriales",
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
