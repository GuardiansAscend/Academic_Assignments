// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------

/**
 * Exception that handles when an attribute is missing from the attribute line of
 * a CSV file.
 * @author Kevin Marnet Scanlan
 */
public class CsvFileInvalidException extends InvalidException{

    /**
     * Default Constructor for a CsvFileInvalidException, creates a default message.
     */
    public CsvFileInvalidException() {
        super("Error: Error in attribute line of CSV file has rendered file invalid");
    }

    /**
     * Parameterized constructor for a CsvFileInvalidException, takes a String
     * input as it's error message.
     * @param message The error message of this CsvFileInvalidException
     */
    public CsvFileInvalidException(String message) {
        super(message);
    }
}
