package com.example.trackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    public static final String DBName = "login.db";

    public DbHelper(Context context) {

        super(context, "login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(name TEXT, username TEXT PRIMARY KEY, password TEXT, repassword TEXT)");
        MyDB.execSQL("create Table tasks(taskname TEXT PRIMARY KEY, taskdesc TEXT, taskdue TEXT)");
        MyDB.execSQL("create Table assignment(groupname TEXT PRIMARY KEY, assigndesc TEXT, assigndue TEXT,unit TEXT, lecturer TEXT)");
        MyDB.execSQL("create Table notes(notename TEXT PRIMARY KEY, notedesc TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("Drop Table if exists users");
        MyDB.execSQL("Drop Table if exists tasks");
        MyDB.execSQL("Drop Table if exists assignment");
        MyDB.execSQL("Drop Table if exists notes");
    }

    public Boolean insertData(String name, String username, String password, String repassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        contentValues.put("Re-password",repassword);
        long result = MyDB.insert( "users", null, contentValues);

        if ( result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkuser( String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username =?", new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkuserpassword (String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where name =? and username =? and password =?", new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
   public Boolean insertData(String taskname, String taskdesc, String taskdue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TaskName", taskname);
        contentValues.put("TaskDescription", taskdesc);
        contentValues.put("TaskDue", taskdue);
        long result = MyDB.insert("tasks", null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean updateData(String taskname,String taskdesc, String taskdue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TaskDescription", taskdesc);
        contentValues.put("TaskDue", taskdue);
        MyDB.update("tasks", contentValues, "taskname =?", new String[]{taskname});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        return MyDB.delete("tasks", "taskname = ?", new String[]{id});
    }


    public Boolean insertData(String notename, String notedesc){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("NoteName",notename);
        contentValues.put("NoteDescription",notedesc);
        long result = MyDB.insert( "notes", null, contentValues);

        if ( result==-1)
            return false;
        else
            return true;
    }
    public Boolean updateData(String notename, String notedesc) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NoteName", notename);
        contentValues.put("NoteDesc", notedesc);
        MyDB.update("notes", contentValues, "notename =?", new String[]{notename});
        return true;
    }

    public boolean deleteData(String notename, String notedesc) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from notes where notename =? and notedesc =?", new String[]{notename, notedesc});
        if (cursor.getColumnCount() > 0) {
            long result = MyDB.delete("assignment", "groupname =?", new String[]{notename, notedesc});

            if (result == -1)
                return false;
            else
                return true;
        }
        else{
            return  false;
        }
    }


    public Boolean insertData(String groupname, String assigndesc, String assigndue, String unit, String lecturer){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("GroupName",groupname);
        contentValues.put("AssignmentDescription",assigndesc);
        contentValues.put("AssignmentDue",assigndue);
        contentValues.put("Unit Name",unit);
        contentValues.put("Lecturer",lecturer);
        long result = MyDB.insert( "assignment", null, contentValues);

        if ( result==-1)
            return false;
        else
            return true;
    }
    public Boolean updateData(String groupname, String assigndesc, String assigndue, String unit, String lecturer) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("GroupName",groupname);
        contentValues.put("AssignmentDescription",assigndesc);
        contentValues.put("AssignmentDue",assigndue);
        contentValues.put("Unit Name",unit);
        contentValues.put("Lecturer",lecturer);
        Cursor cursor = MyDB.rawQuery("select * from assignment where groupname =?", new String[]{groupname});
        if (cursor.getColumnCount() > 0) {
            long result = MyDB.update("assignment", contentValues, "groupname =?", new String[]{groupname});

            if (result == -1)
                return false;
            else
                return true;
        }
        else{
            return  false;
        }
    }

    public Boolean deleteData(String groupname, String assigndesc, String assigndue) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from assignmnet where groupname =? and assigndesc =? and assigndue =?", new String[]{groupname,assigndesc, assigndue});
        if (cursor.getColumnCount() > 0) {
            long result = MyDB.delete("assignment", "groupname =?", new String[]{groupname});

            if (result == -1)
                return false;
            else
                return true;
        }
        else{
            return  false;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor res = MyDB.rawQuery("select * from tasks",null );
        return res;

    }


}

