package com.example.intouch.api;

import com.example.intouch.model.AuthenticationRequest;
import com.example.intouch.model.AuthenticationResponse;
import com.example.intouch.model.ToDoEntity;

import java.util.List;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";
    private OkHttpClient client = new OkHttpClient();
    private MyApiService apiService;

    public AuthApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MyApiService.class);
    }

    public interface AuthApiCallback {
        void onAuthApiResponse(Object response);
        void onAuthApiError(String errorMessage);
    }

    public void login(AuthenticationRequest request, AuthApiCallback callback) {
        Call<AuthenticationResponse> call = apiService.login(request);

        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                if (response.isSuccessful()) {
                    callback.onAuthApiResponse(response.body());
                } else {
                    callback.onAuthApiError("Failed to login. Server returned " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                callback.onAuthApiError("Network error: " + t.getMessage());
            }
        });
    }
}