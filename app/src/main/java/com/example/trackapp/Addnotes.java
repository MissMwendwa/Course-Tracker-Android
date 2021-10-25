package com.example.trackapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addnotes extends AppCompatActivity {
    EditText notename,notedesc;
    Button btnnote, btnupdate, btndelete, btnview;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);

        notename =findViewById(R.id.notename);
        notedesc =findViewById(R.id.notedesc);

        btnnote = findViewById(R.id.btnnote);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btnview =findViewById(R.id.btnview);

        DB = new DbHelper(this);

        btnnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname = notename.getText().toString();
                String ndesc = notedesc.getText().toString();

                Boolean checkinsertData = DB.insertData(nname, ndesc);
                if(checkinsertData == true)
                    Toast.makeText(Addnotes.this, "Kindly add all fields!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addnotes.this, "Note added successfully!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname = notename.getText().toString();
                String ndesc = notedesc.getText().toString();

                Boolean checkupdateData = DB.updateData(nname, ndesc);
                if(checkupdateData == true)
                    Toast.makeText(Addnotes.this, "Note updated successfully!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addnotes.this, "Note not updated!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nname = notename.getText().toString();
                String ndesc = notedesc.getText().toString();

                Boolean checkdeleteData = DB.deleteData(nname, ndesc);
                if(checkdeleteData == true)
                    Toast.makeText(Addnotes.this, "Note updated successfully!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Addnotes.this, "Note not updated!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No Notes Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("NoteName" + res.getString(0) + "\n");
                    buffer.append("NoteDescription" + res.getString(1) + "\n\n");


                    AlertDialog.Builder builder = new AlertDialog.Builder(Addnotes.this);
                    builder.setCancelable(true);
                    builder.setTitle("List of Tasks");
                    builder.setMessage(buffer.toString());
                    builder.show();
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