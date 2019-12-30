package com.example.project.birthday;

import com.google.gson.annotations.SerializedName;

class studentData {
    String _id;
    String name;
    String designation;
    @SerializedName("birthDateMonth")
    String birthDate;
    String birthYear;
    int age;
    public studentData(String name,String _id,String birthDate,String designation,String birthYear) {
        this.name=name;
        this._id=_id;
        this.birthDate=birthDate;
        this.birthYear=birthYear;
        this.designation=designation;
    }
}
