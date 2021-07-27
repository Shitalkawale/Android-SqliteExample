package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtname,edtemail,edtphone,edtaddress;
    Button btnadd,btnview;
    Student st;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;
    sqliteDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname=findViewById(R.id.name);
        edtemail=findViewById(R.id.email);
        edtphone=findViewById(R.id.phone);
        edtaddress=findViewById(R.id.address);
        btnadd=findViewById(R.id.addbtn);
        btnview=findViewById(R.id.viewbtn);
        databaseHelper=new sqliteDatabaseHelper(this);


    }

    public void AddStudent(View view)
    {
        Student student=new Student();
        student.setName(edtname.getText().toString());
        student.setEmail(edtemail.getText().toString());
        student.setPhone(edtphone.getText().toString());
        student.setAddress(edtaddress.getText().toString());
        //student.setAddress(student.getAddress());

        databaseHelper.addStudent(student);
        Toast.makeText(this,"new record inserted sucessfully.....",Toast.LENGTH_LONG).show();


        edtname.setText("");
        edtemail.setText("");
        edtphone.setText("");
        edtaddress.setText("");

    }


    public void ViewStudent(View view)
    {
        startActivity(new Intent(MainActivity.this,MainActivity2.class));
        List<Student> studentList=new ArrayList<>();
        studentList=databaseHelper.viewAllStudent();
        for (Student student:studentList)
        {
            Log.d("Student details"," Name:"+student.getName()+" Email:"+student.getEmail()+" Phone:"+student.getPhone()+" Address:"+student.getAddress());


        }
    }
}