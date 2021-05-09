// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package LocationAddress;
import CommAddress.TelecomAddress;

/**
 * A class that represents a general delivery address.
 * @author Kevin Marnet Scanlan
 */
public class GeneralDeliveryAddress extends GeographicAddress{
    
    /**
     * The telecom address with which to contact the post office of delivery.
     */
    private TelecomAddress telecomAddress;

    /**
     * Default constructor for a GeneralDeliveryAddress, sets the telecom address
     * to its default value..
     */
    public GeneralDeliveryAddress() {
        super();
        telecomAddress = new TelecomAddress();
    }

    /**
     * Parameterized constructor for a GeneralDeliveryAddress, which creates a GeneralDeliveryAddress
     * with the inputted parameters.
     * @param telecomAddress The telecom address of the post office
     * @param addressLine The address line
     * @param city The city 
     * @param regionOrState The region or state
     * @param zipOrPostCode The zip or postal code
     * @param locale The locale
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public GeneralDeliveryAddress(TelecomAddress telecomAddress, String addressLine, 
            String city, String regionOrState, String zipOrPostCode, Locale locale, String validFrom, String validTo) {
        super(addressLine, city, regionOrState, zipOrPostCode, locale, validFrom, validTo);
        this.telecomAddress = telecomAddress;
    }

    /**
     * Copy constructor for a GeneralDeliveryAddress, which creates a new GeneralDeliveryAddress
     * with the same parameters as the passed GeneralDeliveryAddress.
     * @param original The GeneralDeliveryAddress to be copied
     */
    public GeneralDeliveryAddress(GeneralDeliveryAddress original) {
        this(original.telecomAddress, original.addressLine, original.city, original.regionOrState, 
            original.zipOrPostCode, original.locale, original.validFrom, original.validTo);
    }

    /**
     * Getter method for the telecom address.
     * @return The value of the telecom address
     */
    public TelecomAddress getTelecomAddress() {
        return telecomAddress;
    }

    /**
     * Setter method for the telecom address.
     * @param telecomAddress The new telecom address
     */
    public void setTelecomAddress(TelecomAddress telecomAddress) {
        this.telecomAddress = telecomAddress;
    }

    /**
     * toString method, returns the information of this GeneralDeliveryAddress.
     * @return The GeneralDeliveryAddress's info in String form
     */
    @Override
    public String toString() {
        return "This general delivery address has a telecom address of " + telecomAddress.getCountryCode() +
            " " + telecomAddress.getNationalDirectDialingPrefix() + telecomAddress.getAreaCode() + 
                " " + telecomAddress.getNumber() + " ext. " + telecomAddress.getExtension() +
                    " " + telecomAddress.getPhysicalType() + ". " + super.toString();
    }
    
    /**
     * equals method, compares whether the GeneralDeliveryAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        GeneralDeliveryAddress other = (GeneralDeliveryAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.addressLine.equals(other.addressLine) && this.city.equals(other.city) &&
                this.regionOrState.equals(other.regionOrState) && this.zipOrPostCode.equals(other.zipOrPostCode)
                    && this.locale.equals(other.locale) && this.telecomAddress.equals(other.telecomAddress));
    }
}