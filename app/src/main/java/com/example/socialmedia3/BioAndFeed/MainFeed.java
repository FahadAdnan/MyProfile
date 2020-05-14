package com.example.socialmedia3.BioAndFeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialmedia3.Authentication.LoginActivity;
import com.example.socialmedia3.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainFeed extends AppCompatActivity {

    DatabaseReference ref;

    private FirebaseRecyclerOptions<model> options;
    private FirebaseRecyclerAdapter<model, MyViewHolder> adapter;

    private RecyclerView recyclerView;


    private GestureDetectorCompat gestureObject;
    //private Button mdata;
    int p = 0;
    public int z = 0; //why is this here
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mfirebasedatabase;
    private static final String TAG = "ProfileActivity";
    private FirebaseAuth.AuthStateListener mauthlistener;
    private DatabaseReference myref;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);


        ref = FirebaseDatabase.getInstance().getReference().child("Users");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref = mfirebasedatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());


        // new stuff below for recyclerview
        options = new FirebaseRecyclerOptions.Builder<model>().setQuery(ref, model.class).build();
        adapter = new FirebaseRecyclerAdapter<model, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull model model) {
                //setting the value
                holder.textViewEmail.setText(model.getEmail());
                holder.textViewName.setText(model.getName());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent,false);
                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

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
                Intent intent = new Intent (MainFeed.this, BioPage.class);
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
    private void update(final int p) {
        mfirebasedatabase = FirebaseDatabase.getInstance();
        myref = mfirebasedatabase.getReference();
        FirebaseUser user =firebaseAuth.getCurrentUser();

        String userID = user.getUid();
        myref.child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {


                dataSnapshot.getRef().child("points").setValue(p);
                //dialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Users", databaseError.getMessage());
            }
        });
    }
    //this calls on database class
    public void runpage(View view){
        //Intent flash = new Intent(this, Database.class);
        Intent startloginpls = new Intent(this, BioPage.class);
        startActivity(startloginpls);
    }
    public void plus(View view){
        p++;disp(p);}
    public void minus(View view){
        p--;disp(p);
    }
    public void logoutclick(View view){
        firebaseAuth.signOut();
        finish();
        Intent startloginpls = new Intent(this, LoginActivity.class);
        startActivity(startloginpls);}
    private void disp(int p) {
        TextView ok= (TextView) findViewById(R.id.point);
        ok.setText("" + p);
        update(p);}





}