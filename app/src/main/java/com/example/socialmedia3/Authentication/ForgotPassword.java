package com.example.socialmedia3.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener{


    private EditText input_email;
    private Button btnresetpass;
    private TextView btnback;
    private RelativeLayout activity_actually_forgot_password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        input_email = (EditText)findViewById(R.id.forgot_email);
        btnresetpass = (Button)findViewById(R.id.btnresetpassword);
        btnback = (TextView)findViewById(R.id.back);
        activity_actually_forgot_password = (RelativeLayout)findViewById(R.id.activity123);

        btnback.setOnClickListener(this);
        btnresetpass.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == btnback){
            finish();
            Intent internt = new Intent(this, LoginActivity.class);
            startActivity(internt);

        } else if (view == btnresetpass){
            resetpassword(input_email.getText().toString().trim());
        }
    }

    private void resetpassword(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this, "Email sent check your inbox", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        }else{
                            Toast.makeText(ForgotPassword.this, "Failed to send email", Toast.LENGTH_SHORT).show();


                        }}
                });}
}
