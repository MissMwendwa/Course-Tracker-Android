package com.example.trackapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Groupassignments extends AppCompatActivity {

    EditText groupname, assigndesc,assigndue,unit,lecturer;
    Button btnadd, btnupdate, btndelete, btnview;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupassignments);

        groupname =  findViewById(R.id.groupname);
        assigndesc =  findViewById(R.id.assigndesc);
        assigndue =  findViewById(R.id.assigndue);
        unit = findViewById(R.id.unit);
        lecturer =  findViewById(R.id.lecturer);
        DB = new DbHelper(this);

        btnadd=  findViewById(R.id.btnadd);
        btnupdate= findViewById(R.id.btngupdate);
        btndelete=  findViewById(R.id.btngdelete);
        btnview=  findViewById(R.id.btngview);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gname = groupname.getText().toString();
                String gassign = assigndesc.getText().toString();
                String assdue = assigndue.getText().toString();
                String uname = unit.getText().toString();
                String lec = lecturer.getText().toString();

                Boolean checkinsertData = DB.insertData(gname, gassign, assdue, uname,lec);
                if (checkinsertData == true)
                    Toast.makeText(Groupassignments.this, "Add assignment details!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Groupassignments.this, "Assignment added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gname = groupname.getText().toString();
                String gassign = assigndesc.getText().toString();
                String assdue = assigndue.getText().toString();
                String uname = unit.getText().toString();
                String lec = lecturer.getText().toString();

                Boolean checkupdateData = DB.updateData(gname, gassign, assdue, uname,lec);
                if (checkupdateData == true)
                    Toast.makeText(Groupassignments.this, "Assignment details updated!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Groupassignments.this, "Assignment details not updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gname = groupname.getText().toString();
                String gassign = assigndesc.getText().toString();
                String assdue = assigndue.getText().toString();

                Boolean checkdeleteData = DB.deleteData(gname, gassign, assdue);
                if (checkdeleteData == true)
                    Toast.makeText(Groupassignments.this, "Assignment details deleted!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Groupassignments.this, "Assignment details not deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No Data Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("GroupName" + res.getString(0) + "\n");
                    buffer.append("AssignmentDescription" + res.getString(1) + "\n");
                    buffer.append("AssignmentDue" + res.getString(2) + "\n");
                    buffer.append("Unit Name" + res.getString(3) + "\n");
                    buffer.append("Lecturer" + res.getString(4) + "\n\n");

                }
                showMessage("Data", buffer.toString());
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