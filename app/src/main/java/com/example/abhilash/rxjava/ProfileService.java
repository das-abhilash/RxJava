package com.example.abhilash.rxjava;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Abhilash on 1/17/2017.
 */

public interface ProfileService {

    @POST("api/users/get-auth-token.json")
    Observable<User> userSignIn (@QueryMap Map<String, String> user);

    @POST("profile/v2/create-based-on-mobile.json")
    Observable<User> createUser (@QueryMap Map<String, String> user);

    @POST("api/user/generate-otp.json")
    Observable<User> forgotPassword (@QueryMap Map<String, String> user);

}
