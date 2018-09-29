package com.yoeki.kalpnay.hrporatal.Profile.Model;

import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.User;
import com.yoeki.kalpnay.hrporatal.Profile.Model.user_info.UserSend_Data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by IACE on 21-Sep-18.
 */
public interface APIInterface {
    @POST("service1.svc/UserProfile")
    Call<User> idUser(@Body UserSend_Data userId);
}
