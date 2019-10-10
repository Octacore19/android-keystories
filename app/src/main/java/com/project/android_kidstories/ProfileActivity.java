package com.project.android_kidstories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.android_kidstories.Api.ApiInterface;
import com.project.android_kidstories.Api.Client;
import com.project.android_kidstories.Api.Responses.BaseResponse;
import com.project.android_kidstories.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        api = Client.getInstance().create(ApiInterface.class);
    }

    private void loadUserProfile(String token){
        Call<BaseResponse<User>> call = api.getUserProfile(token);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<User>> call, @NonNull Response<BaseResponse<User>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        User user = response.body().getData();
                        String firstname = user.getFirstName();
                        String lastname = user.getLastName();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<User>> call, @NonNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

    private void updateUserProfile(String token){
        String firstname = "";
        String lastname = "";
        User user = new  User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        Call<BaseResponse<User>> call = api.updateUserProfile(token, user);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<User>> call, @NonNull Response<BaseResponse<User>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProfileActivity.this, "User Profile successfully updated", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<User>> call, @NonNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }
}
