package com.example.socialmedia3.BioAndFeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia3.Authentication.User;
import com.example.socialmedia3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BioPage extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private TextView nameStr,ageVal,bioStr,facebookURL;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_page);
        gestureObject = new GestureDetectorCompat(this, new BioPage.LearnGesture());
        nameStr = findViewById(R.id.ProfileName);
        ageVal = findViewById(R.id.ProfileAge);
        bioStr = findViewById(R.id.ProfileBio);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user =firebaseAuth.getCurrentUser();
        String userID = user.getUid();

        databaseReference = firebaseDatabase.getReference();

        //#region Retrieving Data
        databaseReference.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);
                nameStr.setText(userProfile.getName());
                ageVal.setText(userProfile.getAge());
                bioStr.setText(userProfile.getBio());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BioPage.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        //#endregion
    }

    //region swipe
    @Override
    public boolean onTouchEvent (MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY){

            if(event2.getX() > event1.getX()){
                //left to right swipe
                Intent intent = new Intent (BioPage.this, MainFeed.class);
                finish();
                startActivity(intent);
            }

            else
            if (event2.getX() < event1.getX()){
                //right to left swipe
            }
            return true;
        }
    }
    //endregion

    public void edit (View view){
        Intent startloginpls = new Intent(this, EditInfoPage.class);
        startActivity(startloginpls);
    }
}

