package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateView extends AppCompatActivity {

    EditText edtemail,edtphone,edtaddress;
    Button btnupdate;

    sqliteDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_view);


        edtemail=findViewById(R.id.updateemail);
        edtphone=findViewById(R.id.updatephone);
        edtaddress=findViewById(R.id.updateaddress);

        databaseHelper=new sqliteDatabaseHelper(this);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name1");
        String email=intent.getStringExtra("email1");
        String phone=intent.getStringExtra("phone1");
        String address=intent.getStringExtra("address1");


        edtemail.setText(email);
        edtphone.setText(phone);
        edtaddress.setText(address);

        btnupdate=findViewById(R.id.updatebtn);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student();
                student.setName(name);
                student.setEmail(edtemail.getText().toString());
                student.setPhone(edtphone.getText().toString());
                student.setAddress(edtaddress.getText().toString());

                databaseHelper.updateStudent(student);
                Toast.makeText(UpdateView.this,"Student update sucessfully...",Toast.LENGTH_LONG).show();
                notifyAll();
                finish();
            }
        });

    }
}