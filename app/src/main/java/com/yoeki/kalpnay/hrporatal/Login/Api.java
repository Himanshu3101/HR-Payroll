package com.yoeki.kalpnay.hrporatal.Login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "http://192.168.1.238:801/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        // change your base URL
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
          }
        return retrofit;
     }
  }
