package com.example.project.birthday;


import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
    private static Retrofit retrofit=null;
    static Retrofit getClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit=new Retrofit.Builder().baseUrl("https://projects-birthday.devsoc.club").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit;
    }
}
