package FileParser;
// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class takes a file as input, and creates some new Files from the information
 * contained in the first file. Data manipulation is done with ArrayLists.
 * @author Kevin Marnet Scanlan
 */
public class SpecialFilesParser {
    
    /**
     * Main method, which executes the majority of the program.
     * @param args
     */
    public static void main(String[] args) {
         Scanner consoleScanner = new Scanner(System.in);
         
         System.out.println("Hello, and Welcome to the Special Files Parser, "
                 + "where we create wacky files out of your completely normal ones!");
         System.out.println("Please enter the name of your txt file to be converted " +
                 "(Make sure your file is in the same directory as your source code).");
         System.out.print("Enter it here: ");
         
         String fileName = consoleScanner.next();
         if(fileName.length() < 4 || !fileName.substring(fileName.length() - 4).equals(".txt")) 
             fileName += ".txt";
         System.out.println("");
         
         //All necessary scanners and printwriters
        Scanner fileScanner = null;
        PrintWriter vowelPrint = null;
        PrintWriter oPrint = null;
        PrintWriter distinctPrint = null;
         try {
             //ArrayLists, which will contain all info for each page
            fileScanner = new Scanner(new FileInputStream("src/FileParser/" + fileName));
            ArrayList<String> fullArrayList = new ArrayList<>();
            ArrayList<String> vowelArrayList = new ArrayList<>();
            ArrayList<String> oArrayList = new ArrayList<>();
            ArrayList<String> disinctArrayList = new ArrayList<>();
            
             while (fileScanner.hasNext()) {
                 String word = fileScanner.next();
                 //removes non alphanumeric characters
                 word = word.replaceAll("[^a-zA-Z0-9]", "");
                 fullArrayList.add(word);
             }
             fileScanner.close();
             
             for (String word: fullArrayList) {
                 
                 //vowel check
                 if(vowelVerbiageCheck(word))
                     vowelArrayList.add(word);
                 
                 //o check
                 if(word.startsWith("o") || word.startsWith("O"))
                     oArrayList.add(word);
             }
             
             while(!fullArrayList.isEmpty()){
                 //distinct check (removes identical words from the ArrayList as it parses it)
                 String uniqueWord = fullArrayList.get(0);
                 disinctArrayList.add(uniqueWord);
                 while(fullArrayList.remove(uniqueWord)) {}
             }
             
             vowelPrint = new PrintWriter(new FileOutputStream("src/FileParser/Results/vowel_verbiage_of_" + fileName));
             vowelPrint.println("Word Count: " + vowelArrayList.size());
             for (String word : vowelArrayList) {
                 vowelPrint.println(word);
             }
             vowelPrint.close();
             
             oPrint = new PrintWriter(new FileOutputStream("src/FileParser/Results/obsessive_o_of_" + fileName));
             oPrint.println("Word Count: " + oArrayList.size());
             for (String word : oArrayList) {
                 oPrint.println(word);
             }
             oPrint.close();
             
             distinctPrint = new PrintWriter(new FileOutputStream("src/FileParser/Results/distinct_data_of_" + fileName));
             distinctPrint.println("Word Count: " + disinctArrayList.size());
             for (String word : disinctArrayList) {
                 distinctPrint.println(word);
             }
             distinctPrint.close();
             
        } catch (FileNotFoundException e) {
             System.err.println("The file could not be found. Make sure you wrote the file name correctly," +
                     "and that the file is in the correct location. Terminating program.");
             System.exit(0);
        } catch (IOException e) {
             System.err.println("The program has failed to read/write from at least on of the files." +
                     " Please check the validity of your files. Terminating program.");
             System.exit(0);
        }
         
         System.out.println("File successfully parsed! Your wacky files have been generated, and can " +
                 "be found in the Results folder next to your original file.");
         System.out.println("Thank you for using the Special Files Parser!");
    }
    
    /**
     * Method to check if a word has more than 3 vowels.
     * @param word Word to be checked.
     * @return Whether it has 4 or more vowels, as a boolean
     */
    public static boolean vowelVerbiageCheck(String word) {
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        int vowelCounter = 0;
        word = word.toLowerCase();
        for (char c: word.toCharArray()) {
            if(vowels.contains(c))
                vowelCounter++;
        }
        
        return (vowelCounter > 3);
    }
}
