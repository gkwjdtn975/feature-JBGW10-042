package com.nhnacademy;

import java.util.Random;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        Random rand = new Random();
        int number = rand.nextInt(100) + 1;
        System.out.println("java.util.Random number으로 생성된 난수: " + number);

    }
}
