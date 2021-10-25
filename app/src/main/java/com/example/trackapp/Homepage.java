package com.example.trackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    Button btntimetable, btnstudentinfo, btnaddtask;
    Button btnaddnotes, btngroupass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btntimetable = findViewById(R.id.btntimetable);
        btnstudentinfo =  findViewById(R.id.btnstudentinfo);
        btnaddtask =  findViewById(R.id.btnaddtask);
        btnaddnotes =  findViewById(R.id.btnaddnotes);
        btngroupass = findViewById(R.id.btngroupass);



        btntimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Timetable.class);
                startActivity(intent);
            }
        });

        btnstudentinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),studentinformation.class);
                startActivity(intent);
            }
        });

        btnaddtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Addtask.class);
                startActivity(intent);
            }
        });

        btnaddnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),Addnotes.class);
                startActivity(intent);
            }
        });

        btngroupass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Groupassignments.class);
                startActivity(intent);
            }
        });

    }
}