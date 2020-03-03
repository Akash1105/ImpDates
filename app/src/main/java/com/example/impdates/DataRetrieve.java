package com.example.impdates;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataRetrieve extends AppCompatActivity {

    Button b;
    TextView name,date,occasion;
    DatabaseReference reference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_retrieve);

        name = findViewById(R.id.dname);
        date = findViewById(R.id.ddate);
        occasion = findViewById(R.id.doccasion);
        b = findViewById(R.id.dbutt);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("users").child("test1");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String nameee = dataSnapshot.child("test1").getValue().toString();
                        String dob = dataSnapshot.child("dob").getValue().toString();
                        String oca = dataSnapshot.child("occasion").getValue().toString();

//                        name.setText(nameee);
                        date.setText(dob);
                        occasion.setText(oca);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
