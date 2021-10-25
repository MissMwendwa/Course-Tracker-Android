package com.example.trackapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.Buffer;

public class Addtask extends AppCompatActivity {
    EditText taskname, taskdesc,taskdue;
    Button btnnew, btnupdate, btndelete, btnview;
    DbHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        taskname = findViewById(R.id.taskname);
        taskdesc = findViewById(R.id.taskdesc);
        taskdue = findViewById(R.id.taskdue);

        btnnew =  findViewById(R.id.btnnew);
        btnupdate = findViewById(R.id.btntupdate);
        btndelete =  findViewById(R.id.btntdelete);
        btnview=  findViewById(R.id.btntview);

        Db = new DbHelper(this);

        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskn = taskname.getText().toString();
                String taskd = taskdesc.getText().toString();
                String taskdu = taskdue.getText().toString();

                Boolean checkinsertData = Db.insertData(taskn, taskd, taskdu);
                if(!checkinsertData)
                    Toast.makeText(Addtask.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addtask.this, "Task Not Added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskn = taskname.getText().toString();
                String taskd = taskdesc.getText().toString();
                String taskdu = taskdue.getText().toString();

                Boolean checkupdateData = Db.updateData(taskn, taskd, taskdu);
                if(checkupdateData == true)
                    Toast.makeText(Addtask.this, "Task Updated!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addtask.this, "Task not Updated..", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskn = taskname.getText().toString();

                Integer deleteRows = Db.deleteData(taskn);
                if(deleteRows > 0)
                    Toast.makeText(Addtask.this, "Task deleted!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addtask.this, "Task Not Deleted!", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = Db.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No Task Exists!");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("TaskName" + res.getString(0) + "\n");
                    buffer.append("TaskDescription" + res.getString(1) + "\n");
                    buffer.append("TaskDue" + res.getString(2) + "\n\n");
                }
                showMessage("Data",buffer.toString());
            }

        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}