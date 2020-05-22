package com.codewithshreya.justtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main4Activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    EditText roll,dep;
    Button sub,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        roll = (EditText) findViewById(R.id.editText4);
        dep = (EditText) findViewById(R.id.editText5);
        sub = findViewById(R.id.button3);
        reg = findViewById(R.id.button4);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String roool = roll.getText().toString().trim();
                final String depp = dep.getText().toString().trim();
                if (roool.isEmpty()){
                    roll.setError("Required Field");
                }else if (depp.isEmpty()){
                    dep.setError("Required Field");
                }else if (roool.length() < 5){
                    roll.setError("Enter Valid Roll");
                }else {
                    databaseReference.child("Roll").setValue(roool);
                    databaseReference.child("Department").setValue(depp);
                    Toast.makeText(Main4Activity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,Main3Activity.class));
            }
        });

    }
}
