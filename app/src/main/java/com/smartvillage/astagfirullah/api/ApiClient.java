package com.smartvillage.astagfirullah.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://dev.windstandrobotic.org/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
