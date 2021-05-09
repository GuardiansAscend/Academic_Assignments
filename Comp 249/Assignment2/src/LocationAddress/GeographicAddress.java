// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package LocationAddress;
import SimpleAddress.Address;

/**
 * A class that represents a geographic address.
 * @author Kevin Marnet Scanlan
 */
public class GeographicAddress extends Address{

    /**
     * The main address line, such as house number and street name, of a geographic
     * address.
     */
    protected String addressLine;

    /**
     * The city where this geographic address is located.
     */
    protected String city;

    /**
     * The region or state where this geographic address is located.
     */
    protected String regionOrState;

    /**
     * The zip or postal code of this geographic address.
     */
    protected String zipOrPostCode;

    /**
     * The locale that contains this geographic address.
     */
    protected Locale locale;

    /**
     * Default constructor for a GeographicAddress, sets the address line, the city,
     * the region/state, the zip/postal code and the locale to placeholder values.
     */
    public GeographicAddress() {
        super();
        addressLine = "111 Main St.";
        city = "Metroplis";
        regionOrState = "AA";
        zipOrPostCode = "A1A 1A1";
        locale = new Locale();
    }

    /**
     * Parameterized constructor for a GeographicAddress, which creates a GeographicAddress
     * with the inputted parameters.
     * @param addressLine The address line
     * @param city The city 
     * @param regionOrState The region or state
     * @param zipOrPostCode The zip or postal code
     * @param locale The locale
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public GeographicAddress(String addressLine, String city, String regionOrState, 
            String zipOrPostCode, Locale locale, String validFrom, String validTo) {
        super(validFrom, validTo);
        this.addressLine = addressLine;
        this.city = city;
        this.regionOrState = regionOrState;
        this.zipOrPostCode = zipOrPostCode;
        this.locale = locale;
    }

    /**
     * Copy constructor for a GeographicAddress, which creates a new GeographicAddress
     * with the same parameters as the passed GeographicAddress.
     * @param original The GeographicAddress to be copied
     */
    public GeographicAddress(GeographicAddress original) {
        this(original.addressLine, original.city, original.regionOrState, original.zipOrPostCode, 
            original.locale, original.validFrom, original.validTo);
    }

    /**
     * Getter for the address line.
     * @return The value of the address line
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * Setter for the address line.
     * @param addressLine The new address line
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     * Getter for the city.
     * @return The value of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the city.
     * @param city The new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the region or state.
     * @return The value of the region or state
     */
    public String getRegionOrState() {
        return regionOrState;
    }

    /**
     * Setter for the region or state.
     * @param regionOrState The new region or state
     */
    public void setRegionOrState(String regionOrState) {
        this.regionOrState = regionOrState;
    }

    /**
     * Getter for the zip or postal code.
     * @return The value of the zip or postal code
     */
    public String getZipOrPostCode() {
        return zipOrPostCode;
    }

    /**
     * Setter for the zip or postal code.
     * @param zipOrPostCode The new zip or postal code
     */
    public void setZipOrPostCode(String zipOrPostCode) {
        this.zipOrPostCode = zipOrPostCode;
    }

    /**
     * Getter method for the locale.
     * @return The value of the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Setter method for the locale.
     * @param locale The new locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * toString method, returns the information of this GeographicAddress.
     * @return The GeographicAddress's info in String form
     */
    @Override
    public String toString() {
        return "The geographic part of this address is " + addressLine + ", " + city + ", " +
            regionOrState + ", " + zipOrPostCode + ", with a locale of " + locale +
                "\n" + super.toString();
    }
    
    /**
     * equals method, compares whether the GeographicAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        GeographicAddress other = (GeographicAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.addressLine.equals(other.addressLine) && this.city.equals(other.city) &&
                this.regionOrState.equals(other.regionOrState) && this.zipOrPostCode.equals(other.zipOrPostCode)
                    && this.locale.equals(other.locale));
    }
}
