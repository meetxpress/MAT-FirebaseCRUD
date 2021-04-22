package com.example.firebasecrud;

public class Student {
    String studentid, studentname, studentage, studentdegree;
    public Student(){}
    public Student(String studentid, String studentname, String studentage, String studentdegree) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.studentage = studentage;
        this.studentdegree = studentdegree;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentage() {
        return studentage;
    }

    public void setStudentage(String studentage) {
        this.studentage = studentage;
    }

    public String getStudentdegree() {
        return studentdegree;
    }

    public void setStudentdegree(String studentdegree) {
        this.studentdegree = studentdegree;
    }
}
