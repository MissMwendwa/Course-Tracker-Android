package com.example.trackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username, userpassword;
    Button btnsignin;
    DbHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username =  findViewById(R.id.username1);
        userpassword=  findViewById(R.id.userpassword);
        btnsignin =  findViewById(R.id.btnsignin);
        Db = new DbHelper(this);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = userpassword.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = Db.checkuserpassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (getApplicationContext(), Homepage.class);
                    startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}