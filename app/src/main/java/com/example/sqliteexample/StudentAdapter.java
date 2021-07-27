package com.example.sqliteexample;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>
{

    Context context;
    List<Student> studentList;

    sqliteDatabaseHelper databaseHelper;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;

        databaseHelper=new sqliteDatabaseHelper(context);

    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cv= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_design,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        Student student=studentList.get(position);
        holder.txtname.setText(student.getName());
        holder.txtemail.setText(student.getEmail());
        holder.txtphone.setText(student.getPhone());
        holder.txtaddress.setText(student.getAddress());


//        holder.txtname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Clicked on "+student.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteStudent(student.getName());
                Toast.makeText(context,"Data deleted sucessfully...."+student.getName(),Toast.LENGTH_LONG).show();

                notifyDataSetChanged();
            }
        });

        holder.imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateView.class);
                intent.putExtra("name1",holder.txtname.getText().toString());
                intent.putExtra("email1",holder.txtemail.getText().toString());
                intent.putExtra("phone1",holder.txtphone.getText().toString());
                intent.putExtra("address1",holder.txtaddress.getText().toString());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtemail,txtphone,txtaddress;
        ImageView imgdelete,imgupdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.designname);
            txtemail=itemView.findViewById(R.id.designemail);
            txtphone=itemView.findViewById(R.id.designphone);
            txtaddress=itemView.findViewById(R.id.designaddress);
            imgdelete=itemView.findViewById(R.id.deleteimg);
            imgupdate=itemView.findViewById(R.id.updateimg);


        }
    }
}
