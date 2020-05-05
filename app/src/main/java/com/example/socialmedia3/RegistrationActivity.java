package com.example.socialmedia3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuthe;
    private Button buttonregister;
    private EditText usernameer, emailer,passworder,coiner;
    private TextView alreadylogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        buttonregister = (Button)findViewById(R.id.buttonregister);
        usernameer = (EditText) findViewById(R.id.user);
        emailer = (EditText) findViewById(R.id.email);
        passworder = (EditText) findViewById(R.id.password);
        coiner = (EditText) findViewById(R.id.coin);
        alreadylogin = findViewById(R.id.alreadylogin);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseAuthe = FirebaseAuth.getInstance();
        if (firebaseAuthe.getCurrentUser() != null){
            finish();
            //startActivity(new Intent(this, ProfileActivity.class));
        }
    }
    private void registerUser() {
        final String name = usernameer.getText().toString().trim();
        final String email = emailer.getText().toString().trim();
        final String password = passworder.getText().toString().trim();
        final String coin = coiner.getText().toString().trim();
        final String age = "0";
        final String points = "rando";
        final String bio = "no bio yet";
        final String instagram = "empty";
        final String snapchat = "empty";
        final String facebook = "empty";


        //region empty field error check
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegistrationActivity.this, "Enter A Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(RegistrationActivity.this, "NOT A VALID EMAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(RegistrationActivity.this, "NOT A VALID PASSWORD", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(coin)) {
            Toast.makeText(RegistrationActivity.this, "Enter A Coinadress", Toast.LENGTH_SHORT).show();
            return;
        }
        //endregion
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuthe.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (password.length() < 6){
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User currentuser = new User(name, email, coin, points, bio, facebook, snapchat, instagram, age);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(currentuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RegistrationActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Toast.makeText(RegistrationActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void buttonregister(View view) {
        registerUser();
        //Log.e("WOW", "WOOOOW");
    }


    public void alreadylogin(View view){
        finish();
        //   Intent startloginpls = new Intent(this, LoginActivity.class);
        //  startActivity(startloginpls);
    }

}