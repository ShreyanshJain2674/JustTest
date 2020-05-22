package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText name,fname,roll,sem,cat;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button submit = (Button) findViewById(R.id.Image);
        name = (EditText) findViewById(R.id.editText26);
        fname = (EditText) findViewById(R.id.editText27);
        roll = (EditText) findViewById(R.id.editText28);
        sem = (EditText) findViewById(R.id.editText30);
        cat = (EditText) findViewById(R.id.editText31);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Map<String,Object> bd = new HashMap<>();
                bd.put("Name", name.getText().toString());
                bd.put("Father's Name", fname.getText().toString());
                bd.put("Roll Number", roll.getText().toString());
                bd.put("Semester", sem.getText().toString());
                bd.put("Category", cat.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String roll = dataSnapshot.child("Roll").getValue().toString();
                        String dep = dataSnapshot.child("Department").getValue().toString();
                        reference = firebaseDatabase.getReference(dep).child(roll);
                        reference.setValue(bd);
                        Toast.makeText(Main3Activity.this,"Successfull Registration ",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Main3Activity.this,"UnSuccessfull Registration ",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void Signout(View v){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Main3Activity.this,MainActivity.class));
    }
}
