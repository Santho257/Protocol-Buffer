package com.santho.repo;

import com.santho.proto.CollegeProto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentRepoImpl implements StudentRepo{
    private final FileOutputStream studentFos;
    private final FileInputStream studentFis;
    private static StudentRepoImpl studentRepo;

    private StudentRepoImpl() throws IOException {
        this.studentFos = new FileOutputStream("student.txt", true);
        this.studentFis = new FileInputStream("student.txt");
    }

    public static StudentRepoImpl getInstance() throws IOException {
        if(studentRepo == null){
            studentRepo = new StudentRepoImpl();
        }
        return studentRepo;
    }

    @Override
    public void add(CollegeProto.Student student) throws IOException {
        CollegeProto.College college = CollegeProto
                .College
                .newBuilder()
                .mergeFrom(studentFis)
                .addStudents(student)
                .build();
        college.writeTo(studentFos);
    }

    @Override
    public void remove(String regNumber) throws IOException {
        CollegeProto.Student[] students = CollegeProto.College
                .newBuilder()
                .mergeFrom(studentFis)
                .getStudentsList()
                .toArray(new CollegeProto.Student[0]);
        int remIndex = -1;
        for(int i = 0; i < students.length; i++){
            if(students[i].getRegisterNumber().equals(regNumber)){
                remIndex = i;
            }
        }
        if(remIndex == -1) throw new IllegalArgumentException("No student found with Reg No: "+regNumber);
        CollegeProto.College college = CollegeProto.College.newBuilder().removeStudents(remIndex).build();
        college.writeTo(studentFos);
    }

    @Override
    public CollegeProto.Student[] getStudents() throws IOException {
        return CollegeProto.College
                .newBuilder()
                .mergeFrom(studentFis)
                .getStudentsList()
                .toArray(new CollegeProto.Student[0]);
    }
}
