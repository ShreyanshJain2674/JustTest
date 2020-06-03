package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    EditText p1,p2,p3;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref,ref2,ref3,ref4;
    Button sbmt;
    String roll,rank;
    ProgressDialog pd,pd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        p1 = findViewById(R.id.editText4);
        p2 = findViewById(R.id.editText5);
        p3 = findViewById(R.id.editText6);
        sbmt =findViewById(R.id.button3);
        pd = new ProgressDialog(this);
        pd1 = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        pd.setMessage("Hey");
        pd.show();
        ref = firebaseDatabase.getReference(firebaseAuth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank = dataSnapshot.child("Rank").getValue().toString();
                roll = dataSnapshot.child("Roll No").getValue().toString();
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd1.setMessage("Hey");
                pd1.show();
                ref2 = firebaseDatabase.getReference();
                ref2.child(p1.getText().toString()).child("1st Preference").child(rank).child("Roll").setValue(roll);
                ref2.child(p2.getText().toString()).child("2nd Preference").child(rank).child("Roll").setValue(roll);
                ref2.child(p3.getText().toString()).child("3rd Preference").child(rank).child("Roll").setValue(roll);
                Toast.makeText(Main3Activity.this,"Done",Toast.LENGTH_SHORT).show();
                pd1.dismiss();
                ref3 = firebaseDatabase.getReference("Roll Rank").child(rank);
                ref3.child("Roll").setValue(roll);
            }
        });

    }

    public void Logout(View v){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Main3Activity.this,MainActivity.class));
    }
}
