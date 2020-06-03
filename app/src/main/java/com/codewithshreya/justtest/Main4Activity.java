package com.codewithshreya.justtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private Button nxt,done;
    private EditText yr,dep;
    private ListView rollList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,mref,reference,ref2,ref3,ref4;
    List<String> listDataHeader;
    ArrayList<String> selectedItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        nxt = findViewById(R.id.panxt);
        done = findViewById(R.id.padone);
        yr = findViewById(R.id.payr);
        dep = findViewById(R.id.padep);
        rollList = findViewById(R.id.paList);
        rollList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        pd = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllFiles();
            }
        });

        rollList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectItem = ((TextView)view).getText().toString();
                if (selectedItems.contains(selectItem)){
                    selectedItems.remove(selectItem);
                }else {
                    selectedItems.add(selectItem);
                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = selectedItems.size();
                for (int i = 0; i < a;i++){
                    mref = firebaseDatabase.getReference("Final Rank").child(selectedItems.get(i));
                    mref.child("Select").setValue("Yes");
                    //ref2 = firebaseDatabase.getReference("Department").child(yr.getText().toString()).child(selectedItems.get(i));
                    //ref2.child("Select").setValue("Yes");
                    ref2 = firebaseDatabase.getReference(yr.getText().toString()).child(dep.getText().toString()).child(selectedItems.get(i));
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String roll = dataSnapshot.child("Roll").getValue().toString();
                            ref3 = firebaseDatabase.getReference("Final Roll").child(yr.getText().toString()).child(roll);
                            ref3.child("Select").setValue("Yes");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                Toast.makeText(Main4Activity.this,"Done",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewAllFiles() {

        listDataHeader = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference(yr.getText().toString()).child(dep.getText().toString());
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String headertitile = dataSnapshot.getKey();
                listDataHeader.add(headertitile);

                final String[] uploads = new String[listDataHeader.size()];

                for(int i=0; i<uploads.length;i++){
                    uploads[i] = listDataHeader.get(i);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.rowlayout,R.id.txt_lan,uploads);
                /*{

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView myText = (TextView)view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        myText.setTextSize(20);

                        return view;
                    }
                };*/
                rollList.setAdapter(adapter);
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
    }
    public void It2(View v){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Main4Activity.this,MainActivity.class));
    }
}
