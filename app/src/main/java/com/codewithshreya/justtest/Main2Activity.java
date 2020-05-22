package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    private EditText Email,pass;
    private Button go;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText616);
        go = findViewById(R.id.button);

        firebaseAuth = FirebaseAuth.getInstance();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().isEmpty()){
                    Email.setError("Required Field");
                }if (Email.getText().toString().trim().length() < 5){
                    Email.setError("Enter Valid Roll No");
                }if (pass.getText().toString().trim().length() < 6){
                    pass.setError("Password Length must be 6");
                }else {
                    final String mail = Email.getText().toString().trim() + "@nith.ac.in";
                    firebaseAuth.createUserWithEmailAndPassword(mail,pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Main2Activity.this,"Succesfully Created ... Your Email is " + mail , Toast.LENGTH_LONG).show();
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                            }else{
                                Toast.makeText(Main2Activity.this,"Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
