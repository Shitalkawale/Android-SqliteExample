package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    sqliteDatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    List<Student> studentList;
    Student st;
    StudentAdapter studentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseHelper=new sqliteDatabaseHelper(this);

        recyclerView=findViewById(R.id.recyclerview);

        studentList=new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList=databaseHelper.viewAllStudent();
        studentAdapter=new StudentAdapter(this,studentList);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
    }
}