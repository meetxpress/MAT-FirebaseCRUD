package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Insert extends AppCompatActivity {

    EditText sId, sName, sAge, sDegree;
    Button btnInsertData;
    ArrayList<Student> students;
    DatabaseReference databaseStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        sId = findViewById(R.id.sId);
        sName = findViewById(R.id.sName);
        sAge = findViewById(R.id.sAge);
        sDegree = findViewById(R.id.sDegree);
        students = new ArrayList<Student>();
        btnInsertData = findViewById(R.id.btnInsertData);
        databaseStudent = FirebaseDatabase.getInstance().getReference("students");

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(sName.getText().toString()) && !TextUtils.isEmpty(sAge.getText().toString()) && !TextUtils.isEmpty(sDegree.getText().toString())) {
                    String stdId = databaseStudent.push().getKey();
                    Student s = new Student(stdId, sName.getText().toString(), sAge.getText().toString(), sDegree.getText().toString());
                    databaseStudent.child(stdId).setValue(s);
                    sId.setText("");
                    sName.setText("");
                    sAge.setText("");
                    sDegree.setText("");
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}