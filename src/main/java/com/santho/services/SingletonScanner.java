package com.santho.services;

import java.util.Scanner;

public class SingletonScanner {
    private static Scanner in;
    public static  Scanner getScanner(){
        if(in == null)  in = new Scanner(System.in);
        return in;
    }
}
