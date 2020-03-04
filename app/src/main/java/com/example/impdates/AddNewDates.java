package com.example.impdates;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class AddNewDates extends Activity implements View.OnClickListener {
    Button addD;
    RadioGroup radioGroup;
    EditText name, occasion, dateet;
    TextView frequencyTV;
    CheckBox ch1;
    final Calendar myCalendar = Calendar.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_content);
        addD = findViewById(R.id.button1);
        ch1=(CheckBox)findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup1);
        frequencyTV = findViewById(R.id.FrequencyTextView);
        name = findViewById(R.id.nameEditText);
        occasion = findViewById(R.id.occasionEditText);
        dateet = findViewById(R.id.dateEditText);
        addD.setOnClickListener(this);

        dateet.setKeyListener(null);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddNewDates.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateet.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("users");
        String namee = name.getText().toString();
//        myRef.push().setValue(namee);
        myRef = database.getReference("users").child(namee);
       // myRef.child("name").setValue(name.getText());
        myRef.child("dob").setValue(dateet.getText().toString());
        myRef.child("occasion").setValue(occasion.getText().toString());
        myRef.child("remindme").setValue("true");
        myRef.child("frequency").setValue("one");
        myRef.child("name").setValue(name.getText().toString());
                name.setText("");
                dateet.setText("");
                occasion.setText("");
                ch1.setChecked(false);
                radioGroup.clearCheck();
                frequencyTV.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);

//        DatabaseReference myRef1 = database.getReference("users");
//
//        myRef = database.getReference("users").child(child);


    }

    public void toRemind(View view){
        if(ch1.isChecked()){
            radioGroup.setVisibility(View.VISIBLE);
            frequencyTV.setVisibility(View.VISIBLE);
        }else{
            radioGroup.setVisibility(View.INVISIBLE);
            frequencyTV.setVisibility(View.INVISIBLE);
        }
    }
}
