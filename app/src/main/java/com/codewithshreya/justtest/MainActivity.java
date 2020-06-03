package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Email,pass;
    private TextView SignUp;
    private Button signin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.editText2);
        pass = findViewById(R.id.editText3);
        SignUp = findViewById(R.id.textView);
        signin = findViewById(R.id.button2);
        firebaseAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(Email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (Email.getText().toString().trim().equals("admin@gmail.com")){
                                startActivity(new Intent(MainActivity.this,Main4Activity.class));
                            }else if (Email.getText().toString().equals("admin2@gmail.com")){
                                startActivity(new Intent(MainActivity.this,Main5Activity.class));
                            }else if (Email.getText().toString().equals("admin3@gmail.com")){
                                startActivity(new Intent(MainActivity.this,Main6Activity.class));
                            }else {
                                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,Main3Activity.class));
                            }
                        }else {
                            Toast.makeText(MainActivity.this,"UnSuccessfull",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
}
