package com.example.project.birthday;

import com.example.project.birthday.StudentAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {


        @GET
        public  Call<List<studentData>> getInfo(@Url String url);

        @POST("./info")
        Call<Task> postInfo(@Body Task task);
    }

