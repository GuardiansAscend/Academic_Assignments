/**
 * Assignment 1
 * Written by: Kevin Marnet Scanlan, 40175644
 * For COMP 248 Section S 2202 - Fall 2020
 */

/*
This program prompts the user for two integers, the second integer being a non-zero
integer, and then calculates the addition, subtraction, multiplicaion and division
of these two integers together, and returns the results to the user in the output.
*/
import java.util.Scanner;
public class callMeCalc {
    
    public static void main(String[] args) {
        
        //This is the Welcome Message.
        System.out.println("----------------------------------------------------------");
        System.out.println("Hello! You don't know me, but you can call me Calculator!");
        System.out.println("----------------------------------------------------------");
        System.out.println("\nPlease enter two different integers seperated by a space");
        System.out.println("(WARNING: Please do not enter 0 as the second integer)");
        
        //This is where the integers the user inputted are initiated.
        Scanner scanner = new Scanner(System.in);
        int firstNumber = scanner.nextInt();
        int secondNumber = scanner.nextInt();
        
        //This is where the results are outputted, the math is done at the same 
        //time as the output.
        System.out.println("\nYou entered \"" + firstNumber + "\" and \"" + secondNumber + "\"");
        System.out.println(firstNumber + " + " + secondNumber + " = " + (firstNumber + secondNumber));
        System.out.println(firstNumber + " - " + secondNumber + " = " + (firstNumber - secondNumber));
        System.out.println(firstNumber + " * " + secondNumber + " = " + (firstNumber * secondNumber));
        System.out.println(firstNumber + " / " + secondNumber + " = " + (firstNumber * 1.0 / secondNumber));
        
        //This is the Closing Message.
        System.out.println("\nVoila! Thank you for using me as your Calculator!");
    }
}
