package com.example.socialmedia3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerpage(View view){
        finish();
        startActivity( new Intent(this, RegistrationActivity.class));
    }

    public void loginpage(View view){
        finish();
        //   Intent startloginpls = new Intent(this, LoginActivity.class);
        //  startActivity(startloginpls);
    }







}
