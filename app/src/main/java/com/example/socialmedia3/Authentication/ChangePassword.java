package com.example.socialmedia3.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialmedia3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private EditText oldpass,newpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldpass = (EditText)findViewById(R.id.oldpasswordin);
        newpass = (EditText)findViewById(R.id.newpasswordin);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }


    public void changepassword (View view){

        progressDialog.setMessage("Changing Password");
        progressDialog.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            user.updatePassword(oldpass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                if (oldpass == newpass){
                                    progressDialog.dismiss();
                                    Toast.makeText(ChangePassword.this,"Successfully changed password",Toast.LENGTH_LONG).show();
                                    Intent startloginpls = new Intent(ChangePassword.this, LoginActivity.class);
                                    startActivity(startloginpls);}

                            } else if (oldpass != newpass){
                                progressDialog.dismiss();
                                Toast.makeText(ChangePassword.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(ChangePassword.this,"Error Compiled",Toast.LENGTH_LONG).show();
                            }

                        }
                    });}}
}
