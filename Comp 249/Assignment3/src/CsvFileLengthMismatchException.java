// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------

/**
 * Exception class that handles when the data field size does not match the
 * attribute field size of a CSV file.
 * @author Kevin Marnet Scanlan
 */
public class CsvFileLengthMismatchException extends InvalidException{

    /**
     * Default Constructor for a CsvFileLengthMismatchException, creates a default message.
     */
    public CsvFileLengthMismatchException() {
        super("Error: A mismatch in the number of data fields in the CSV file has rendered object invalid");
    }

    /**
     * Parameterized constructor for a CsvFileLengthMismatchException, takes a String
     * input as it's error message.
     * @param message The error message of this CsvFileLengthMismatchException
     */
    public CsvFileLengthMismatchException(String message) {
        super(message);
    }
}
