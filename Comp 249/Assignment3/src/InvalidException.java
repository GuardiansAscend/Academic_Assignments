// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------

/**
 * Exception class that handles invalid lines in a CSV file.
 * @author Kevin Marnet Scanlan
 */
public class InvalidException extends Exception{

    /**
     * Default constructor for an InvalidException, creates a default message.
     */
    public InvalidException() {
        super("Error: Input row cannot be parsed due to missing information");
    }

    /**
     * Parameterized constructor for an InvalidException, takes a String input as
     * it's error message.
     * @param message The error message of this InvalidException
     */
    public InvalidException(String message) {
        super(message);
    }
}
