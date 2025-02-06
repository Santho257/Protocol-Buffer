package com.santho;

import com.santho.services.SingletonScanner;
import com.santho.services.StudentService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentService studentService = new StudentService();
        Scanner in = SingletonScanner.getScanner();
        outerLoop:while (true){
            System.out.println("Enter\n1. Add student\n2. Remove Student\n3. View Students\n4. Exit");
            int choice;
            while (true){
                try {
                    choice = Integer.parseInt(in.nextLine());
                    break;
                }
                catch (NumberFormatException ex){
                    System.out.println("Enter Numbers Only");
                }
            }
            switch (choice){
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    studentService.removeStudent();
                    break;
                case 3:
                    studentService.printStudents();
                    break;
                case 4:
                    break outerLoop;
                default:
                    System.out.println("Enter Valid Number Only");
            }
        }

    }
}