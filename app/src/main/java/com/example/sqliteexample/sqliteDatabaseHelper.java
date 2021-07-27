package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class sqliteDatabaseHelper extends SQLiteOpenHelper
{
    public static final int Data_Base_Version=1;
    public static final String DatabaseName="mydatabase";
    public static final String TABLE_NAME="student";
    public final String name="name";
    public final String email="email";
    public final String address="address";
    public final String phone="phoneno";




    public sqliteDatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, Data_Base_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_create="CREATE TABLE "+TABLE_NAME+"("+name+" varchar(100),"+email+" varchar(100),"+address+" varchar(100),"+phone+" varchar(100)"+")";
        db.execSQL(table_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table="DROP TABLE "+TABLE_NAME;
        db.execSQL(drop_table);
    }

    public void addStudent(Student student)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,student.getName());
        contentValues.put(email,student.getEmail());
        contentValues.put(address,student.getAddress());
        contentValues.put(phone,student.getPhone());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }

    public List<Student> viewAllStudent()
    {
        List<Student> studentList=new ArrayList<>();
        Student student=new Student();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sqlquery="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(sqlquery,null);

        if(cursor.moveToFirst())
        {
            do{
                Student student1=new Student();
                student1.setName(cursor.getString(0));
                student1.setEmail(cursor.getString(1));
                student1.setAddress(cursor.getString(2));
                student1.setPhone(cursor.getString(3));

                studentList.add(student1);

            }while (cursor.moveToNext());
        }
        return studentList;

    }

    public void deleteStudent(String name1){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,name+"=?",new String[]{name1});
        sqLiteDatabase.close();
    }

    public void updateStudent(Student student)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,student.getName());
        contentValues.put(email,student.getEmail());
        contentValues.put(phone,student.getPhone());
        contentValues.put(address,student.getAddress());

        sqLiteDatabase.update(TABLE_NAME,contentValues,name+"=?",new String[]{student.getName()});
        sqLiteDatabase.close();

    }


}
