package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    private Button refresh;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,mref1,mref2,mref3,cref1,cref2,cref3,eref1,eref2,eref3;
    List<String> listDataHeader;
    String[] uploads;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        refresh = findViewById(R.id.button5);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        listDataHeader = new ArrayList<>();

        pd = new ProgressDialog(this);

        pd.setMessage("Wait");
        pd.show();
        databaseReference = firebaseDatabase.getReference("Final Rank");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String headertitile = dataSnapshot.getKey();
                listDataHeader.add(headertitile);

                uploads = new String[listDataHeader.size()];

                for(int i=0; i<uploads.length;i++){
                    uploads[i] = listDataHeader.get(i);
                }
                pd.dismiss();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mref1 = firebaseDatabase.getReference("MECHANICAL").child("1st Preference");
                mref2 = firebaseDatabase.getReference("MECHANICAL").child("2nd Preference");
                mref3 = firebaseDatabase.getReference("MECHANICAL").child("3rd Preference");
                cref1 = firebaseDatabase.getReference("CSE").child("1st Preference");
                cref2 = firebaseDatabase.getReference("CSE").child("2nd Preference");
                cref3 = firebaseDatabase.getReference("CSE").child("3rd Preference");
                eref1 = firebaseDatabase.getReference("ECE").child("1st Preference");
                eref2 = firebaseDatabase.getReference("ECE").child("2nd Preference");
                eref3 = firebaseDatabase.getReference("ECE").child("3rd Preference");

                for (int j = 0; j < uploads.length; j++){
                    mref1.child(uploads[j]).removeValue();
                    mref2.child(uploads[j]).removeValue();
                    mref3.child(uploads[j]).removeValue();
                    cref1.child(uploads[j]).removeValue();
                    cref2.child(uploads[j]).removeValue();
                    cref3.child(uploads[j]).removeValue();
                    eref1.child(uploads[j]).removeValue();
                    eref2.child(uploads[j]).removeValue();
                    eref3.child(uploads[j]).removeValue();
                }
                Toast.makeText(Main5Activity.this,"Done",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Logout1(View v){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Main5Activity.this, MainActivity.class));
    }
}
