package com.example.socialmedia3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText emailin;
    private EditText passwordin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        emailin = findViewById(R.id.emaili);
        passwordin = findViewById(R.id.passwordi);

    }

    private void checksignin(){
        String emailinput = emailin.getText().toString().trim();
        String passwordinput = passwordin.getText().toString().trim();

        if (TextUtils.isEmpty(emailinput)){
            Toast.makeText(LoginActivity.this, "Not a valid EMAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordinput)){
            Toast.makeText(LoginActivity.this, "Incorrect PASSWORD/EMAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailinput,passwordinput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //auto fills once your write this and then select onComplete listener
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Successful sign up", Toast.LENGTH_SHORT).show();
                            //finish();
                            //startActivity(new Intent(getApplicationContext(), biopage.class));

                        }else{
                            Toast.makeText(LoginActivity.this, "Failed to sign up", Toast.LENGTH_SHORT).show();
                        }}});}


    public void notregistered(View view) {
        finish();
        Intent startMainActivity = new Intent(this, RegistrationActivity.class);
        startActivity(startMainActivity);
    }

    public void buttonsignin(View view) {
        checksignin();
    }

    public void forgotpassword(View view){
        finish();
        Intent startForgotPassActivity = new Intent(this, ForgotPassword.class);
        startActivity(startForgotPassActivity);
    }
}