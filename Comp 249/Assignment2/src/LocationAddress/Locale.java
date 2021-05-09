// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package LocationAddress;

/**
 * A class that represents a locale.
 * @author Kevin Marnet Scanlan
 */
public class Locale {
    
    /**
     * The alphanumeric country code of the locale.
     */
    private String alphaCountryCode;
    
    /**
     * The numerical country code of the locale.
     */
    private int numCountryCode;
    
    /**
     * The name of the country in plain English.
     */
    private String engCountryName;

    /**
     * Default constructor for a Locale, sets the alphanumeric country code,
     * the numerical country code and the country name to placeholder values.
     */
    public Locale() {
        alphaCountryCode = "XX";
        numCountryCode = 0;
        engCountryName = "Country Name";
    }

    /**
     * Parameterized constructor for a Locale, which creates a Locale with the 
     * inputted parameters.
     * @param alphaCountryCode The alphanumeric country code
     * @param numCountryCode The numerical country code
     * @param engCountryName The name of the country in English
     */
    public Locale(String alphaCountryCode, int numCountryCode, String engCountryName) {
        this.alphaCountryCode = alphaCountryCode;
        this.numCountryCode = numCountryCode;
        this.engCountryName = engCountryName;
    }
    
    /**
     * Copy constructor for a Locale, which creates a new Locale with the same 
     * parameters as the passed Locale.
     * @param original The Locale to be copied
     */
    public Locale(Locale original){
        this(original.alphaCountryCode, original.numCountryCode, original.engCountryName);
    }

    /**
     * Getter method for the alphanumeric country code.
     * @return The value of the alphanumeric country code
     */
    public String getAlphaCountryCode() {
        return alphaCountryCode;
    }

    /**
     * Setter method for the alphanumeric country code.
     * @param alphaCountryCode The new alphanumeric country code
     */
    public void setAlphaCountryCode(String alphaCountryCode) {
        this.alphaCountryCode = alphaCountryCode;
    }

    /**
     * Getter method for the numerical country code.
     * @return The value of the numerical country code
     */
    public int getNumCountryCode() {
        return numCountryCode;
    }

    /**
     * Setter method for the numerical country code.
     * @param numCountryCode The new numerical country code
     */
    public void setNumCountryCode(int numCountryCode) {
        this.numCountryCode = numCountryCode;
    }

    /**
     * Getter method for the country name.
     * @return Value of the country name
     */
    public String getEngCountryName() {
        return engCountryName;
    }

    /**
     * Setter method for the country name.
     * @param engCountryName The new country name
     */
    public void setEngCountryName(String engCountryName) {
        this.engCountryName = engCountryName;
    }

    /**
     * toString method, returns the information of this Locale.
     * @return The Locale's info in String form
     */
    @Override
    public String toString() {
        return "\"" + alphaCountryCode + "\" " + numCountryCode + " \"" + engCountryName + "\"";
    }
    
    /**
     * equals method, compares whether the Locale is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        Locale other = (Locale) obj;
        return (this.alphaCountryCode.equals(other.alphaCountryCode) && this.numCountryCode == other.numCountryCode
            && this.engCountryName.equals(other.engCountryName));
    }
}
