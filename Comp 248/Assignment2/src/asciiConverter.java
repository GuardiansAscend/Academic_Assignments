// -------------------------------------------------------
// Assignment 2
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This program takes a character (any letter of the english alphabet), converts that
character into its ASCII value, and finds if that ASCII value is divisible by 2,
3, 5 or 7, and displays the results to the user.
*/
import java.util.Scanner;
public class asciiConverter {

    public static void main(String[] args) {
        //This is the welcome and prompt messages, and the Scanner is also initiated
        //in this section.
        System.out.println("Hello! Welcome to the Ascii Code Converter.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a letter in the English alphabet: ");
        
        //Here the user input is taken, turned into a character, and subsequently
        //we collect the ascii value of that character.
        String userInput = scanner.next();
        char letter = userInput.charAt(0);
        int asciiValue = (int) letter;
        
        //Here some booleans are instatiated to be later used to find the factors 
        //of the ascii value.
        boolean check2, check3, check5, check7;
        
        //Here switch statements are used to check what the factors of the ascii
        //value are.
        switch(asciiValue % 2) {
            case 0 : check2 = true; 
                break;
            default: check2 = false;
        }
        switch(asciiValue % 3) {
            case 0 : check3 = true; 
                break;
            default: check3 = false;
        }
        switch(asciiValue % 5) {
            case 0 : check5 = true; 
                break;
            default: check5 = false;
        }
        switch(asciiValue % 7) {
            case 0 : check7 = true; 
                break;
            default: check7 = false;
        }
        
        //This is where we formulate the String to be outputted to the user, and
        //then display it in the console.
        if(check2) {
            System.out.println("The ASCII number of " + letter + " is the even number " + asciiValue + ".");
            System.out.println("It is a multiple of 2.");
        }
        else
            System.out.println("The ASCII number of " + letter + " is the odd number " + asciiValue + ".");
        if(check3)
            System.out.println("It is a multiple of 3.");
        if(check5)
            System.out.println("It is a multiple of 5.");
        if(check7)
            System.out.println("It is a multiple of 7.");
        if(!(check2 || check3 || check5 || check7))
            System.out.println("All of 2, 3, 5 and 7 are not factors of this number.");
        
        //This is the closing message.
        System.out.println("Conversion Complete. Program will now terminate.");
    }   
}
