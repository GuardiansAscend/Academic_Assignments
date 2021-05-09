// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package CommAddress;
import SimpleAddress.Address;

/**
 * A class that represents a telecom address.
 * @author Kevin Marnet Scanlan
 */
public class TelecomAddress extends Address{
    
    /**
     * The international direct dialing prefix of the telecom address, also known
     * as the country code.
     */
    private String countryCode;
    
    /**
     * The national direct dialing prefix of the telecom address.
     */
    private String nationalDirectDialingPrefix;
    
    /**
     * The area code of the telecom address.
     */
    private int areaCode;
    
    /**
     * The unique number of the telecom address.
     */
    private long number;
    
    /**
     * The extension of telephone number of the telecom address.
     */
    private int extension;
    
    /**
     * The physical type of the telecom object that uses the telecom address.
     */
    private String physicalType;

    /**
     * Default constructor for a TelecomAddress, sets the country code, NDD prefix, 
     * area code, telephone number, extension and physical type to placeholder values.
     */
    public TelecomAddress() {
        super();
        countryCode = "+1";
        nationalDirectDialingPrefix = "(0)";
        areaCode = 100;
        number = 1111111;
        extension = 0;
        physicalType = "undefined";
    }

    /**
     * Parameterized constructor for a TelecomAddress, which creates a TelecomAddress
     * with the inputted parameters.
     * @param countryCode The country code of address
     * @param nationalDirectDialingPrefix The national direct dialing prefix of the address
     * @param areaCode The area code of the telecom address
     * @param number The telephone number
     * @param extension The number's relveant extension
     * @param physicalType The physical type of communication device related to this 
     * telecom address
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public TelecomAddress(String countryCode, String nationalDirectDialingPrefix, 
            int areaCode, long number, int extension, String physicalType, String validFrom, String validTo) {
        super(validFrom, validTo);
        this.countryCode = countryCode;
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
        this.areaCode = areaCode;
        this.number = number;
        this.extension = extension;
        this.physicalType = physicalType;
    }

    /**
     * Copy constructor for a TelecomAddress, which creates a new TelecomAddress
     * with the same parameters as the passed TelecomAddress.
     * @param original The TelecomAddress to be copied
     */
    public TelecomAddress(TelecomAddress original) {
        this(original.countryCode, original.nationalDirectDialingPrefix, original.areaCode, 
            original.number, original.extension, original.physicalType, original.validFrom, original.validTo);
    }

    /**
     * Getter method for the country code.
     * @return The value of the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter method for the country code.
     * @param countryCode The new country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Getter method for the national direct dialing prefix.
     * @return The value of the national direct dialing prefix
     */
    public String getNationalDirectDialingPrefix() {
        return nationalDirectDialingPrefix;
    }

    /**
     * Setter method for the national direct dialing prefix.
     * @param nationalDirectDialingPrefix The new national direct dialing prefix
     */
    public void setNationalDirectDialingPrefix(String nationalDirectDialingPrefix) {
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
    }

    /**
     * Getter method for the area code.
     * @return The value of the area code
     */
    public int getAreaCode() {
        return areaCode;
    }

    /**
     * Setter method for the area code.
     * @param areaCode The new area code
     */
    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Getter method for the telephone number.
     * @return The value of the telephone number
     */
    public long getNumber() {
        return number;
    }

    /**
     * Setter method for the telephone number.
     * @param number The new telephone number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Getter method for the extension.
     * @return The value of the extension
     */
    public int getExtension() {
        return extension;
    }

    /**
     * Setter method for the extension.
     * @param extension The new extension
     */
    public void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Getter method for the physical type.
     * @return The value of the physical type
     */
    public String getPhysicalType() {
        return physicalType;
    }

    /**
     * Setter method for the physical type.
     * @param physicalType The new physical type
     */
    public void setPhysicalType(String physicalType) {
        this.physicalType = physicalType;
    }

    /**
     * toString method, returns the information of this TelecomAddress.
     * @return The TelecomAddress's info in String form
     */
    @Override
    public String toString() {
        return "This telecom address is " + countryCode + " " + nationalDirectDialingPrefix + 
            areaCode + " " + number + " ext. " + extension + " " + physicalType + "\n" + super.toString();
    }
    
    /**
     * equals method, compares whether the TelecomAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        TelecomAddress other = (TelecomAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.countryCode.equals(other.countryCode) && this.nationalDirectDialingPrefix.equals(other.nationalDirectDialingPrefix)
                && this.areaCode == other.areaCode && this.number == other.number &&
                    this.extension == other.extension && this.physicalType.equals(other.physicalType));
    }
}
