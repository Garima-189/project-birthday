package com.example.project.birthday;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private Toolbar toolbar;
    private Menu menu;
    private FloatingActionButton fab;
    private List<studentData> studentDataList = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
       // menu= MenuInflater.inflate();
        adapter = new StudentAdapter(studentDataList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        toolbar=findViewById(R.id.toolbar);
        fab=findViewById(R.id.fab);
        toolbar.setTitle("Birthday Application");
        setSupportActionBar(toolbar);
        prepareData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent);



            }
            });
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void prepareData()
    {
        studentDataList.add(new studentData("test","test","00-00","test","0000"));
        APIInterface placeHolderAPI=APIClient.getClient().create(APIInterface.class);
        Date date= Calendar.getInstance().getTime();
        Calendar calendar=Calendar.getInstance();
        String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month=String.valueOf(calendar.get(Calendar.MONTH));


        DateFormat df=new SimpleDateFormat("dd-MM-YYYY");


        String formattedDate=df.format(date);
       // Toast.makeText(getApplicationContext(),formattedDate.substring(0,5),Toast.LENGTH_LONG).show();

        //date.getDay()
        //String formattedDate="27-12-2019";


        Call<List<studentData>> call=placeHolderAPI.getInfo("./getInfo/"+formattedDate.substring(0,5));
        call.enqueue(new Callback<List<studentData>>() {
            @Override
            public void onResponse(Call<List<studentData>> call, Response<List<studentData>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Code:"+response.toString(),Toast.LENGTH_LONG).show();


                    return;
                }
                List<studentData> posts=response.body();
                String l=""+posts.size();
               //r Toast.makeText(getApplicationContext(),l,Toast.LENGTH_LONG).show();
                // post.add(posts);

                // if(posts!=null)
                for(studentData post:posts)
                {
                  studentDataList.add(new studentData(post.name,post._id,post.birthDate,post.designation,post.birthYear));
                  adapter.notifyDataSetChanged();

                }
                //Toast.makeText(getApplicationContext(),studentDataList.get(1).getId(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<List<studentData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.exit)
            System.exit(0);
        return(super.onOptionsItemSelected(item));

    }

}
