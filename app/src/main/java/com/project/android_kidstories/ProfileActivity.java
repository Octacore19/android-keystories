package com.project.android_kidstories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.android_kidstories.Api.ApiInterface;
import com.project.android_kidstories.Api.Client;
import com.project.android_kidstories.Api.Responses.BaseResponse;
import com.project.android_kidstories.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    private void loadUserProfile(String token){
        Call<BaseResponse<User>> call = Client.getInstance().create(ApiInterface.class).getUserProfile(token);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<User>> call, @NonNull Response<BaseResponse<User>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        User user = response.body().getData();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<User>> call, @NonNull Throwable t) {

            }
        });
    }
}
