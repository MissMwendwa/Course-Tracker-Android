package com.example.trackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, username, password, repassword;
    Button signup, loginpage;
    DbHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Name);
        username =  findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword =  findViewById(R.id.repassword);

        signup =  findViewById(R.id.btnsignup);
        loginpage =  findViewById(R.id.btnloginpage);
        DB = new DbHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = name.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(!sname.equals("")||user.equals("")||pass.equals("")||repass.equals("")) {
                    DB.insertData(sname, user, pass, repass);
                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(intent);
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkuser(user);
                        if(!checkuser){
                            Boolean insert = DB.insertData(sname, user,pass,repass);

                        } else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                /*else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkuser(user);
                        if(!checkuser){
                            Boolean insert = DB.insertData(sname, user,pass,repass);




                        } else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                            Toast.makeText(MainActivity.this, "User already exists! Sign in", Toast.LENGTH_SHORT).show();
                        }

                }else{
                        Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    } */

                    }
                }
       };
        });

        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    };
}

