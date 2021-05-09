import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------

/**
 * A Driver class that takes CSV files provided by the user and provides equivalent
 * JSON files with the information from the CSV files. Also gives the option to display
 * the contents of the created JSON files in the console.
 * @author Kevin Marnet Scanlan
 */
public class CsvToJsonDriver {
    
    /**
     * The Main method, within which the user is prompted and methods related to
     * the CSV to JSON transfer are called.
     * @param args
     */
    public static void main(String[] args) {
        //Sets errors to be displayed in an error logging file
        try {
            System.setErr(new PrintStream(new FileOutputStream("ErrorLog.txt", true)));
        } catch (FileNotFoundException e) {
            System.out.println("Problem with the error logging file, please fix before starting program");
            System.exit(1);
        }
        
        //Prompts the user to input how many CSV files they want to transfer to JSON.
        //If a natural number is not entered, user is reprompted until a natural number
        //is entered
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("CSV2JSON program initiated. Please enter the number of files to be converted: ");
        int fileNum;
        while (true) {            
            if(consoleScanner.hasNextInt()) {
                fileNum = consoleScanner.nextInt();
                if (fileNum > 0) 
                    break;
                
                System.out.print("Sorry, the value provided was invalid. Please enter an integer above 0: ");
                continue;
            }
            
            System.out.print("Sorry, that is not a whole number. Please enter an integer above 0: ");
            consoleScanner.next();
        }
        
        
        //Creates a File array, in which all the CSV files will be stored while we
        //create their JSON counterparts
        System.out.println("\nIt is time to enter the name of each file to be converted. " +
                "Please place all desired CSV files in the src folder, next to the source code.");
        File[] csvFiles = new File[fileNum];
        //Clears the line
        consoleScanner.nextLine();
        //Prompts the user for each CSV file name, as many times as the user had
        //indicated previously. User may input each name with or without the ".csv" 
        //suffix. User must have their CSV files in the src folder
        for (int i = 0; i < fileNum; i++) {
            System.out.print("Enter the name of file " + (i + 1) + ": ");
            String fileName = consoleScanner.nextLine();
            if(fileName.length() < 4 || !fileName.substring(fileName.length() - 4).equals(".csv")) { 
                File csvFile = new File("src/" + fileName + ".csv");
                csvFiles[i] = csvFile;
            }
            else {
                File csvFile = new File("src/" + fileName);
                csvFiles[i] = csvFile;
            }
        }
        //Creates a folder to store the JSON files once they are created
        File jsonFolder = new File("src/JSONresults");
        jsonFolder.mkdir();
        //Each valid JSON file is created here
        for (File csvFile : csvFiles) {
            CsvToJsonDriver.processFileForValidation(csvFile);
        }
        
        System.out.println("Any file conversion that was possible has happened." + 
                " The results are located in the JSONresults folder, which is in the same location as the csv files.");
        //Allows the user to display any desired JSON file from the folder in the
        //console. If user provide 2 invalid file names in a row, the program exits.
        for(int i = 0; i < 2; i++) {
            System.out.println("To exit the program, enter " + (2 - i) + " more incorrect file name(s).");
            System.out.print("Alternatively, enter the name of one of the created files to display it in the console: ");
            //Resets the counter if a valid file name is given
            if(CsvToJsonDriver.jsonRead(consoleScanner.nextLine()))
                i = -1;
            
        }
        
        System.out.println("Second incorrect file name has been entered. Shutting down program.");
    }
    
    /**
     * Method that validates the passed CSV file, and if valid, creates the corresponding
     * JSON file from the CSV file information. Handles all potential exceptions
     * that could be thrown during the conversion process.
     * @param csvFile The CSV file to be validated and transferred to JSON
     */
    public static void processFileForValidation(File csvFile) {
        //Initialization of necessary data
        Scanner scanner = null;
        PrintWriter pw = null;
        String [] csvAttributes = null;
        String [] csvObject = null;
        //This boolean is a check for if the current object is the first or not
        boolean hasPrev = false;
        try {
            //Scanner setup on the file, and attributes of the file taken in an array
            //Could throw FileNotFoundException here
            scanner = new Scanner(new FileInputStream(csvFile));
            //Could throw ArrayIndexOutofBoundsException here
            csvAttributes = quotationFormatting(scanner.nextLine().split(","));
            
            //Checks for missing attributes, throws CsvFileInvalid Exception if any are missing
            for (String csvAttribute : csvAttributes) {
                if (csvAttribute.isEmpty()) {
                    throw new CsvFileInvalidException("File " + csvFile.getName() + " is invalid: attribute field is missing." +
                            "\nFile conversion skipped.");
                }
            }
            
            //These catch clauses activate if the file cannot be read, and will cause
            //the JSON file to not be created
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + csvFile.getName() + " for reading.");
            System.out.println("Please check if file exists! This file conversion will be skipped.");
            System.err.println("File " + csvFile.getName() + " could not be found nor opened.\n");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File " + csvFile.getName() + " is invalid: lone leading quotation mark.\n" + 
                    "File conversion skipped.");
            System.err.println("File " + csvFile.getName() + " is invalid: leading quotation mark has no closing quotation mark.");
            System.err.println("Attribute boundaries unclear. File has been skipped.\n");
            scanner.close();
            return;
        } catch (CsvFileInvalidException e) {
            System.out.println(e.getMessage());
            System.err.println("File " + csvFile.getName() + " is invalid. In the following attribute fields, at least one is empty: ");
            System.err.println(Arrays.toString(csvAttributes) + "\n");
            scanner.close();
            return;
        }
        
        
        try {
            //Creates the JSON file for the Csv File
            //While this could throw a FileNotFoundException, it should not do so
            //except in very special conditions
            pw = new PrintWriter(new FileOutputStream("src/JSONresults/" + csvFile.getName().substring(0, csvFile.getName().length() - 4) + ".json"));
            pw.print("[\n");
            for (int i = 2; scanner.hasNextLine(); i++) {
                try{
                    //Reads the next line of data from the CSV file
                    //Could Throw ArrayIndexOutOfBoundsException here
                    csvObject = quotationFormatting(scanner.nextLine().split(","));
                    //Checks for missing data fields, throws CsvFileDataMissingException if any are missing
                    for (String dataString : csvObject) {
                        if(dataString.isEmpty()) {
                            throw new CsvFileDataMissingException("In file " + csvFile.getName() + ", line " + i + " cannot be converted to Json: data is missing." +
                                    "\nLine " + i + " is skipped.");
                        }
                    }
                    //Checks that each data field corresponds to an attribute, throws
                    //CsvFileLengthMismatchException if there is a discrepancy
                    if(csvAttributes.length != csvObject.length) {
                        throw new CsvFileLengthMismatchException("In file " + csvFile.getName() + ", line " + i + " cannot be converted to Json: data/attribute length mismatch." +
                                "\nLine " + i + " is skipped.");
                    }
                    
                    //Formats the data in JSON style and prints it into the file
                    //adds a comma if the object is not the first one
                    if(hasPrev)
                        pw.print(",\n" + jsonFormatting(csvAttributes, csvObject));
                    else {
                        pw.print(jsonFormatting(csvAttributes, csvObject));
                        hasPrev = true;
                    }
                    
                    //These catch clauses activate if a data line could not be read,
                    //and causes a skip over a single line of data
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("In file " + csvFile.getName() + ", line " + i + " cannot be converted to Json: lone leading quotation mark.\n" + 
                            "Line " + i + " is skipped.");
                    System.err.println("In file " + csvFile.getName() + ", line " + i + ": leading quotation mark has no closing quotation mark.");
                    System.err.println("Data boundaries unclear. Line has been skipped.\n");
                } catch (CsvFileDataMissingException e) {
                    System.out.println(e.getMessage());
                    System.err.println("In file " + csvFile.getName() + ", line " + i + ": In the following data fields, at least one is empty: ");
                    System.err.println(Arrays.toString(csvObject) + "\n");
                } catch (CsvFileLengthMismatchException e) {
                    System.out.println(e.getMessage());
                    System.err.println("In file " + csvFile.getName() + ", line " + i + ": the number of atributes is not equivalent to the amount of data in this object:");
                    System.err.println("Number of attributes: " + csvAttributes.length + ". Number of data fields: " + csvObject.length + ".\n");
                }
            }
            
            //Closing of all the files
            pw.print("\n]");
            scanner.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not create " + csvFile.getName().substring(0, csvFile.getName().length() - 4) + ".json for writing.\n" + 
                    "File conversion skipped.");
            System.err.println("File " + csvFile.getName().substring(0, csvFile.getName().length() - 4) + ".json could not be created or opened.\n");
            scanner.close();
        } 
    }
    
    /**
     * Method that makes sure that Strings surrounded by quotation marks are taken
     * as a single string. Only used by the processFileForValidation method.
     * @param arrayNoFormat The String array to be correctly reformatted
     * @return The correctly formatted String array
     * @throws ArrayIndexOutOfBoundsException, which will happen if a leading
     * quotation mark has no closing quotation mark
     */
    private static String[] quotationFormatting(String[] arrayNoFormat) throws ArrayIndexOutOfBoundsException{
        //An ArrayList is used to temporarily store the array of unknown length
        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < arrayNoFormat.length; i++) {
            String nextStr = arrayNoFormat[i];
            if(arrayNoFormat[i].startsWith("\"")) {
                while(!arrayNoFormat[i].endsWith("\"")) {
                    //If there is no closing quotation mark, this will throw an 
                    //ArrayIndexOutOfBoundsException, which is handled in the
                    //processFileForValidation method
                    nextStr += "," + arrayNoFormat[i + 1];
                    i++;
                }
                tempArray.add(nextStr.substring(1, nextStr.length() - 1));
            }
            else
                tempArray.add(nextStr);
        }
        
        //This will create a new String array, that is identical to the ArrayList,
        //and that has the correct size
        return tempArray.toArray(new String[0]);
    }
    
    /**
     * Method that takes a set of attributes with corresponding data, and returns
     * a correctly formatted JSON object. The object is returned as a String, so it
     * can be input directly into the JSON file. Only used by the processFileForValdation method.
     * @param attributes The String array of attributes from the CSV file
     * @param data The Data that corresponds to the attributes from the CSV file
     * @return A JSON object in String representation
     */
    private static String jsonFormatting(String [] attributes, String [] data) {
        String jsonObj = " {\n";
        for(int i = 0; i < data.length; i++) {
            jsonObj += "  \"" + attributes[i] + "\": \"" + data[i] + "\"";
            if(i != (data.length - 1))
                jsonObj += ",\n";
            else
                jsonObj += "\n }";
        }
        
        return jsonObj;
    }
    
    /**
     * Method that will read a JSON file that is present in the JSONresults folder.
     * It will read through the entire file using BufferedReader, and display the
     * results in the console.
     * @param fileName The name of the file in the folder, taken as a String
     * @return A boolean that is true when the file was present in the folder and 
     * could be read, false otherwise.
     */
    public static boolean jsonRead(String fileName) {
        BufferedReader buffRead = null;
        
        try{
            //Enables user to either input the file name with or without ".json"
            //Could throw FileNotFoundException here
            if(fileName.length() < 5 || !fileName.substring(fileName.length() - 5).equals(".json")) { 
                buffRead = new BufferedReader(new FileReader("src/JSONresults/" + fileName + ".json"));
            }
            else {
                buffRead = new BufferedReader(new FileReader("src/JSONresults/" + fileName));
            }
            
            //Outputs the JSON file into the console
            //Could throw IOException here
            String output = buffRead.readLine();
            while (output != null) {
                System.out.println(output);
                output = buffRead.readLine();
            }
            System.out.println("");
            //Could also throw IOException here
            buffRead.close();
            return true;
            
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + fileName + " for reading.");
            System.out.println("Please check if file exists!\n");
            System.err.println("File " + fileName + " could not be found nor opened.\n");
            return false;
        } catch (IOException e) {
            System.out.println("Encountered an error while reading through the file. Please check the file for errors!\n");
            System.err.println("File " + fileName + " could not be read properly without errors.\n");
            return true;
        }
    }
}