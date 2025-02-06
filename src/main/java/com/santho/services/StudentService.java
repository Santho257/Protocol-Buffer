package com.santho.services;

import com.santho.proto.CollegeProto;
import com.santho.repo.StudentRepo;
import com.santho.repo.StudentRepoImpl;

import java.io.IOException;
import java.util.Scanner;

public class StudentService {
    private final StudentRepo studentRepo;
    private final Scanner in;

    public StudentService() throws IOException {
        this.studentRepo = StudentRepoImpl.getInstance();
        in = SingletonScanner.getScanner();
    }

    public void addStudent() throws IOException {
        System.out.println("Enter Register Number");
        String regNum = in.nextLine();
        System.out.println("Enter Name");
        String name = in.nextLine();
        int choice;
        while (true){
            System.out.println("Enter 1 if Has CGPA\nEnter other if not");
            try{
                choice = Integer.parseInt(in.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Enter Valid Number");
            }
        }
        double cgpa = -1;
        if(choice == 1){
            System.out.println("Enter CGPA");
            cgpa = in.nextDouble();
        }
        if(cgpa != -1){
            studentRepo.add(CollegeProto.Student.newBuilder()
                            .setRegisterNumber(regNum)
                            .setName(name)
                            .setCGPA(cgpa)
                    .build());
        }
        else {
            studentRepo.add(CollegeProto.Student.newBuilder()
                    .setRegisterNumber(regNum)
                    .setName(name)
                    .build());
        }
        System.out.println("Student with reg No "+regNum+" added successfully!");
    }

    public void removeStudent() throws IOException {
        System.out.println("Enter Register Number");
        String regNum = in.nextLine();
        try {
            studentRepo.remove(regNum);
        }
        catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Student with reg No "+regNum+" removed successfully!");
    }

    public void printStudents() throws IOException {
        CollegeProto.Student[] students = studentRepo.getStudents();
        for(CollegeProto.Student student:students){
            System.out.println(student.toString());
        }
    }
}
