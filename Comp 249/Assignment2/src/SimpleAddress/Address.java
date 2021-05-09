// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package SimpleAddress;

/**
 * The base Address class, that contains the default information any Address should
 * have.
 * @author Kevin Marnet Scanlan
 */
public class Address {

    /**
     * The first date on which this Address is valid.
     */
    protected String validFrom;

    /**
     * The last date on which this Address is valid.
     */
    protected String validTo;
    
    /**
     * Default Address constructor, sets the valid dates to the year 2021.
     */
    public Address() {
        validFrom = "2021-01-01";
        validTo = "2021-12-31";
    }

    /**
     * Parameterized Address constructor, which creates an Address with inputted 
     * parameters.
     * @param validFrom The beginning validity date
     * @param validTo The ending validity date
     */
    public Address(String validFrom, String validTo) {
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
    
    /**
     * The Address copy constructor, which creates a new Address with the same
     * parameters as the passed Address.
     * @param original The Address to be copied
     */
    public Address(Address original) {
        this(original.validFrom, original.validTo);
    }
    
    /**
     * Getter for the first valid date.
     * @return Value of the first valid date
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * Setter for the first valid date.
     * @param validFrom The new first valid date
     */
    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Getter for the last valid date.
     * @return Value of the last valid date
     */
    public String getValidTo() {
        return validTo;
    }

    /**
     * Setter for the last valid date.
     * @param validTo The new last valid date
     */
    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
    
    /**
     * Method that checks if the Address is obsolete on the inputted date.
     * @param year Inputted year
     * @param month Inputted month
     * @param day Inputted day
     * @return True if the address is obsolete, false otherwise
     */
    public boolean isObsolete(int year, int month, int day) {
        String[] validFromParts = validFrom.split("-");
        String[] validToParts = validTo.split("-");
        int fromYear = Integer.parseInt(validFromParts[0]);
        int fromMonth = Integer.parseInt(validFromParts[1]);
        int fromDay = Integer.parseInt(validFromParts[2]);
        int toYear = Integer.parseInt(validToParts[0]);
        int toMonth = Integer.parseInt(validToParts[1]);
        int toDay = Integer.parseInt(validToParts[2]);
        
        if(year < fromYear || year > toYear)
            return true;
        
        if(year == fromYear) {
            if(month < fromMonth)
                return true;
            if(month == fromMonth) { 
                if(day < fromDay)
                    return true;
            }
        }
        
        if(year == toYear) {
            if(month > toMonth)
                return true;
            if(month == toMonth) {
                if(day > toDay)
                    return true;
            }
        }
        
        return false;
    }
    
    /**
     * toString method, returns the information of the Address.
     * @return The Address's info in String form
     */
    @Override
    public String toString() {
        return "This specific address is/was valid between " + validFrom + " and " +
            validTo;
    }
    
    /**
     * equals method, compares whether this Address is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        Address other = (Address) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo));
    }
}
