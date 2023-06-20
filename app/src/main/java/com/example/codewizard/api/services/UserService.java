package com.example.codewizard.api.services;

import com.example.codewizard.api.ApiClient;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.HttpMethod;
import com.example.codewizard.api.model.Usuario;
import com.example.codewizard.singleton.CurrentUser;

import java.util.concurrent.CompletableFuture;

public class UserService {
    private static final String CREDENTIALS = CurrentUser.getInstance().getToken();
    private static final String AUTH_METHOD = "Bearer";

    /**
     *
     * @param usuario
     * @return ApiResponse object
     */
    public static ApiResponse updateProfile(Usuario usuario){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/updateprofile",
                HttpMethod.PATCH,
                AUTH_METHOD,
                CREDENTIALS,
                usuario
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
     * @param oldPassword
     * @param newPassword
     * @return ApiResponse object
     */
    public static ApiResponse updatePassword(int idUsuario, String oldPassword, String newPassword){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/updatepassword",
                HttpMethod.PATCH,
                AUTH_METHOD,
                CREDENTIALS,
                new UserPasswordChange(
                        idUsuario,
                        oldPassword,
                        newPassword
                )
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
     * @param email
     * @param username
     * @return ApiResponse object
     */
    public static ApiResponse sendOtp(String email, String username){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setUsername(username);

        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/sendotp",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                usuario
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
     * @param username
     * @param email
     * @param oldPassword
     * @param newPassword
     * @return ApiResponse object
     */
    public static ApiResponse recoverPassword(String username, String email, String oldPassword, String newPassword){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/recoverpassword",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                new UserPasswordChange(
                        username,
                        email,
                        oldPassword,
                        newPassword
                )
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
     * @param user
     * @return ApiResponse object
     */
    public static ApiResponse deleteProfile(Usuario user){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/deleteprofile",
                HttpMethod.DELETE,
                AUTH_METHOD,
                CREDENTIALS,
                user
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
     * @param username
     * @return ApiResponse object
     */
    public static ApiResponse findUser(String username){
        String endpoint = "api/users/finduser" + "/" + username;
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
     * @param username
     * @return ApiResponse object
     */
    public static ApiResponse userProfile(String username){
        String endpoint = "api/users/profile" + "/" + username;
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
     * @param idUser
     * @return ApiResponse object
     */
    public static ApiResponse userLibrary(int idUser){
        String endpoint = "api/users/library" + "/" + idUser;
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
     * @param subject
     * @param content
     * @return ApiResponse object
     */
    public static ApiResponse sendBroadcast(String subject, String content){
        CompletableFuture<ApiResponse> future = ApiClient.sendRequest(
                "api/users/broadcast",
                HttpMethod.POST,
                AUTH_METHOD,
                CREDENTIALS,
                new BroadcastEmail(subject,content)
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

    public static ApiResponse getAllUsers( ){
        String endpoint = "api/users/allUsers";
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

class UserPasswordChange{
    private int idUsuario;
    private String username;
    private String email;
    private String oldPassword;
    private String newPassword;

    public UserPasswordChange(int idUsuario, String oldPassword, String newPassword) {
        this.idUsuario = idUsuario;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UserPasswordChange(String username, String email, String oldPassword, String newPassword) {
        this.username = username;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}

class BroadcastEmail{
    private String subject;
    private String content;

    public BroadcastEmail(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}