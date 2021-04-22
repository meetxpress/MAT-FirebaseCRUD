package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    ListView lvDisplay;
    ArrayList<Student> students;
    DatabaseReference databaseStudent;
    private static final String sId = "com.example.firebasecrud.sId";
    private static final String sName = "com.example.firebasecrud.sName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        students = new ArrayList<Student>();
        btnInsert = findViewById(R.id.btnInsert);
        lvDisplay = findViewById(R.id.lvDisyplay);
        databaseStudent = FirebaseDatabase.getInstance().getReference("students");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Insert.class));
            }
        });

        lvDisplay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Student st = students.get(pos);
                showDialog(st.getStudentid(), st.getStudentname());
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students.clear();
                for (DataSnapshot postSnapshots : snapshot.getChildren()) {
                    Student st = postSnapshots.getValue(Student.class);
                    students.add(st);
                    StudentList stli = new StudentList(MainActivity.this, students);
                    lvDisplay.setAdapter(stli);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public boolean updateStudent(String id, String stdName, String stdAge, String stdDeg) {
        DatabaseReference dbstd = FirebaseDatabase.getInstance().getReference("students").child(id);
        Student std = new Student(id, stdName, stdAge, stdDeg);
        dbstd.setValue(std);
        Toast.makeText(this, "Record Updated!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void showDialog(final String sId, String sName) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_update, null);
        ab.setView(v);
        EditText etStd = v.findViewById(R.id.up_sName);
        EditText etAge = v.findViewById(R.id.up_sAge);
        EditText etDegree = v.findViewById(R.id.up_sDegree);
        Button btn = v.findViewById(R.id.btnUpdateData);
        Button btndel = v.findViewById(R.id.btnDeleteData);

        ab.setTitle("Update User Data");
        AlertDialog aa = ab.create();
        ab.show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = etStd.getText().toString();
                String sage = etAge.getText().toString();
                String sdeg = etDegree.getText().toString();

                if (sname != "" || sage != "" || sdeg != "") {
                    Log.d("sn", sname);
                    Log.d("sa", sage);
                    Log.d("sd", sdeg);
                    updateStudent(sId, sname, sage, sdeg);
                    aa.dismiss();
                } else{
                    Toast.makeText(getApplicationContext(), "Insert Proper Values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rf=FirebaseDatabase.getInstance().getReference("students").child(sId);
                rf.removeValue();
                Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                aa.dismiss();
            }
        });
    }
}