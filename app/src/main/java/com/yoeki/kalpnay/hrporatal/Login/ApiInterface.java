package com.yoeki.kalpnay.hrporatal.Login;

import com.yoeki.kalpnay.hrporatal.HomeMenu.Changepassresponce;
import com.yoeki.kalpnay.hrporatal.Payroll.EarningDetail;
import com.yoeki.kalpnay.hrporatal.Payroll.Paystructuremodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //@FormUrlEncoded // annotation used in POST type requests
    @POST("service1.svc/LoginUser")
    Call<Loginresponce>login(@Body Loginresponce user);

    @POST("service1.svc/ForgetPassword")
    Call<Forgotresponce>forgot(@Body Forgotresponce user);


    @POST("service1.svc/ChangePassword")
    Call<Changepassresponce>changepass(@Body Changepassresponce user);

    @POST("service1.svc/PayRoll")
    Call<Paystructuremodel>paystructure(@Body Paystructuremodel user);

    @POST("service1.svc/SaveLeave")
    Call<Paystructuremodel>saveleave(@Body Paystructuremodel user);

    @GET("service1.svc/PayRoll?")
    Call<List<EarningDetail>> doGetEarningList();

}
