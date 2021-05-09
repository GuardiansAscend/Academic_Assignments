// -------------------------------------------------------
// Assignment 2
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This program takes a sentence in a specific format from the user, takes key informtaion
from that sentence and forms another sentence with the new info, outputting back
to the user. The program runs infinitely until the user enters the prompted sentinel
value "x".
*/
import java.util.Scanner;
public class miniTranslator {

    public static void main(String[] args) {
        //This is the welcome message
        System.out.println("------------------------------");
        System.out.println("  Infinite Translator v20.20  ");
        System.out.println("------------------------------");
        System.out.println("Hello, and welcome to the INFINITE TRANSLATOR");
        System.out.println("I can translate forever :)");
        
        //This is the scanner that will take the user inputs, followed by the loop
        //which contains all the logic.
        Scanner  scanner =  new Scanner(System.in);
        while(true) {
            
            //This is where we prompt the user to enter their sentence and collect
            //their input.
            System.out.print("Please enter the input sentence (enter x to exit): ");
            String userInput = scanner.nextLine();
            
            //This exits the program if the user entered the sentinel value "x"
            if(userInput.equals("x"))
                break;
            
            //Here the user's sentence is split into its different words, and then
            //each portion of information is initiated into its own String. The year
            //is also converted to an integer.
            String [] sentenceInfo = userInput.split(" ", 7);
            String name = sentenceInfo[0];
            String city = sentenceInfo[3].substring(0, sentenceInfo[3].length() - 1);
            String country = sentenceInfo[4];
            String yearString = sentenceInfo[6].substring(0, sentenceInfo[6].length() - 1);
            int year = Integer.parseInt(yearString);
            
            //Here we output the translated sentence
            System.out.println("\n" + name + " stayed in " + city + " for " + (2020 - year)
                    + " year(s). " + city + " is in " + country + ".\n");
        }
        
        //This is the closing message
        System.out.println("\nThank you for using the INFINITE TRANSLATOR");
        System.out.println("Come back any time :)");
    }
}
