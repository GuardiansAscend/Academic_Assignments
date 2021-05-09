// -------------------------------------------------------
// Assignment 3
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This is a program that takes 10 integer values from the user at inputs them into
an array. The duplicate values are removed from the array, and the unique set of
integer values from the array are outputted to the user.
*/
import java.util.Scanner;
public class duplicateElim {
    
    public static void main(String[] args) {
        
        //This is the welcome message.
        System.out.println("Welcome to the Duplicate Number Eliminator.");
        System.out.println("Please enter 10 numbers betwee 10 and 100, inclusive.");
        
        //Here we initialize the scanner and the array of empty numbers
        Scanner scanner = new Scanner(System.in);
        int [] numbers = {-1, -1, -1, -1, -1, -1, -1, -1, -1 , -1};
        
        //This loop prompts the user for the 10 values to be put into the array.
        //If a value is entered from outside the valid bounds, the user is reprompted
        //to enter a valid integer for that value. All valid values are input into
        //the array int the order they are input.
        for (int i = 0; i < 10; i++) {
            
            System.out.print("Enter value " + (i + 1) + ": ");
            int inputValue = scanner.nextInt();
            
            while(inputValue < 10 || inputValue > 100){
                System.out.print("Sorry, that is an invalid value. Please enter an"
                        + "integer in the range [10, 100]: ");
                inputValue = scanner.nextInt();
            }
            
            numbers[i] = inputValue;
        }
        
        //These loops check the array contents for duplicates. If a duplicate pair
        //is found, the later iteration of the duplicate is set to -1.
        for (int i = 0; i < 10; i++) {
            if(numbers[i] == -1)
                continue;
            
            for (int j = (i + 1); j < 10; j++) {
                if(numbers[i] == numbers[j])
                    numbers[j] = -1;
            }
        }
        
        //Here all the non-empty (unique) values in the array are arranged in the
        //order they were originally entered into the array.
        System.out.println("Here are the number array's unique value(s): ");
        String output = "";
        for (int i = 0; i < 10; i++) {
            if(numbers[i] != -1)
                output += numbers[i] + " ";
        }
        
        //All the unique values are outputted, and the closing message is displayed.
        System.out.println(output);
        System.out.println("That is all from the Duplicate Number Eliminator! Shutting down...");
    }
}
