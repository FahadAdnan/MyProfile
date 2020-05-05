package com.example.socialmedia3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditInfoPage extends AppCompatActivity {

    private EditText nameval,ageval, pointval, bioval, faceval, gramval, snapval;

    private String namevals,agevals, pointvals, biovals, facevals, gramvals, snapvals;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mfirebasedatabase;
    private static final String TAG = "editinfo";
    private FirebaseAuth.AuthStateListener mauthlistener;
    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_page);



        firebaseAuth = FirebaseAuth.getInstance();
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref=mfirebasedatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        nameval = (EditText) findViewById(R.id.name);
        ageval = (EditText) findViewById(R.id.age);
        pointval = (EditText) findViewById(R.id.points);
        bioval = (EditText) findViewById(R.id.bio);
        faceval = (EditText) findViewById(R.id.facebookId);
        gramval = (EditText) findViewById(R.id.instagramId);
        snapval = (EditText) findViewById(R.id.snapchatId);
    }

    public void save(View view) {
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref = mfirebasedatabase.getReference();
        FirebaseUser user =firebaseAuth.getCurrentUser();

        namevals = nameval.getText().toString();
        agevals = ageval.getText().toString();
        pointvals = pointval.getText().toString();
        biovals = bioval.getText().toString();
        facevals = faceval.getText().toString();
        gramvals = gramval.getText().toString();
        snapvals = snapval.getText().toString();


        String userID = user.getUid();
        myref.child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("name").setValue(namevals);
                dataSnapshot.getRef().child("age").setValue(agevals);
                dataSnapshot.getRef().child("bio").setValue(biovals);
                dataSnapshot.getRef().child("points").setValue(pointvals);
                dataSnapshot.getRef().child("facebook").setValue(facevals);
                dataSnapshot.getRef().child("instagram").setValue(gramvals);
                dataSnapshot.getRef().child("snapchat").setValue(snapvals);
                //dialog.dismiss();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Users", databaseError.getMessage());
            }
        });
      //  Intent backToBio = new Intent(this, biopage.class);
      //  startActivity(backToBio);
    }

}


