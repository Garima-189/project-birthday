package com.example.project.birthday;

import com.google.gson.annotations.SerializedName;

public class Task {
    private String name;
    private String designation;
    @SerializedName("birthDateMonth")
    private String birthDate;
    private String birthYear;
    public Task(String name,String designation,String birthDate,String birthYear)
    {
        this.name=name;
        this.designation=designation;

        this.birthDate=birthDate;
        this.birthYear=birthYear;
    }
}
