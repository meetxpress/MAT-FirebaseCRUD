package com.example.firebasecrud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends ArrayAdapter<Student> {
    Activity context;
    ArrayList<Student> students;

    public StudentList(@NonNull Activity context, ArrayList<Student> students) {
        super(context, R.layout.layout_student_list, students);
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_student_list, null, true);

        TextView tvstdName = v.findViewById(R.id.tvDisName);
        TextView tvstdAge = v.findViewById(R.id.tvDisAge);
        TextView tvstdDegree = v.findViewById(R.id.tvDisDeg);
        Student stu = students.get(position);

        tvstdName.setText("Name: " + stu.getStudentname());
        tvstdAge.setText("Age: " + stu.getStudentage());
        tvstdDegree.setText("Degree: " + stu.getStudentdegree());
        return v;
    }
}
