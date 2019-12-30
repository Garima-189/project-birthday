package com.example.project.birthday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private TextView name;
    private TextView designation;
    private TextView birth_date;
    private Button post_button;
  //  private TextView birth_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        name = findViewById(R.id.name_text);
        designation = findViewById(R.id.designation_text);
        birth_date = findViewById(R.id.birthday_text);
       // birth_year=findViewById(R.id.birthyear_text);

        post_button = findViewById(R.id.post_button);
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_, designation_, birth_date_,birth_year_;
                name_ = String.valueOf(name.getText());
                designation_ = String.valueOf(designation.getText());
                birth_date_ = String.valueOf(birth_date.getText()).substring(0,5);
                birth_year_=String.valueOf(birth_date.getText()).substring(6);
               // birth_year_=String.valueOf(birth_year.getText());
                Task task = new Task(name_, designation_, birth_date_,birth_year_);
                APIInterface placeHolderAPI = APIClient.getClient().create(APIInterface.class);
                Call<Task> call = placeHolderAPI.postInfo(task);
                call.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Toast.makeText(getApplicationContext(),"Posted Successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Try again later",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

    }
}
