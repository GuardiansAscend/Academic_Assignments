// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------

/**
 * Exception class that handles when a piece of data is missing from a line in a 
 * CSV file.
 * @author Kevin Marnet Scanlan
 */
public class CsvFileDataMissingException extends InvalidException{

    /**
     * Default Constructor for a CsvFileDataMissingException, creates a default message.
     */
    public CsvFileDataMissingException() {
        super("Error: Error in a data line of CSV file has rendered object invalid");
    }

    /**
     * Parameterized constructor for a CsvFileDataMissingException, takes a String
     * input as it's error message.
     * @param message The error message of this CsvFileDataMissingException
     */
    public CsvFileDataMissingException(String message) {
        super(message);
    }
}
