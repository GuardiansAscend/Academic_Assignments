// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package LocationAddress;

/**
 * A class that represents a post office box address.
 * @author Kevin Marnet Scanlan
 */
public class PostOfficeBoxAddress extends GeographicAddress{
    
    /**
     * The door code for the lobby the box is located in.
     */
    private int boxLobbyDoorCode;

    /**
     * Default constructor for a PostOfficeBoxAddress, sets the door code for the 
     * for the post office lobby to a placeholder value.
     */
    public PostOfficeBoxAddress() {
        super();
        boxLobbyDoorCode = 1234;
    }

    /**
     * Parameterized constructor for a PostOfficeBoxAddress, which creates a PostOfficeBoxAddress 
     * with the inputted parameters.
     * @param boxLobbyDoorCode The door code for the lobby
     * @param addressLine The address line
     * @param city The city 
     * @param regionOrState The region or state
     * @param zipOrPostCode The zip or postal code
     * @param locale The locale
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public PostOfficeBoxAddress(int boxLobbyDoorCode, String addressLine, String city, 
            String regionOrState, String zipOrPostCode, Locale locale, String validFrom, String validTo) {
        super(addressLine, city, regionOrState, zipOrPostCode, locale, validFrom, validTo);
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    /**
     * Copy constructor for a PostOfficeBoxAddress, which creates a new PostOfficeBoxAddress
     * with the same parameters as the passed PostOfficeBoxAddress.
     * @param original The PostOfficeBoxAddress to be copied
     */
    public PostOfficeBoxAddress(PostOfficeBoxAddress original) {
        this(original.boxLobbyDoorCode, original.addressLine, original.city, original.regionOrState, 
            original.zipOrPostCode, original.locale, original.validFrom, original.validTo);
    }

    /**
     * Getter method for the lobby's door code.
     * @return The value of the lobby's door code
     */
    public int getBoxLobbyDoorCode() {
        return boxLobbyDoorCode;
    }

    /**
     * Setter method for the lobby's door code.
     * @param boxLobbyDoorCode The new door code for the lobby
     */
    public void setBoxLobbyDoorCode(int boxLobbyDoorCode) {
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    /**
     * toString method, returns the information of this PostOfficeBoxAddress.
     * @return The PostOfficeBoxAddress's info in String form
     */
    @Override
    public String toString() {
        return "This post-office box address has a box lobby door code of " + 
            boxLobbyDoorCode + ". " + super.toString();
    }
    
    /**
     * equals method, compares whether the PostOfficeBoxAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        PostOfficeBoxAddress other = (PostOfficeBoxAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.addressLine.equals(other.addressLine) && this.city.equals(other.city) &&
                this.regionOrState.equals(other.regionOrState) && this.zipOrPostCode.equals(other.zipOrPostCode)
                    && this.locale.equals(other.locale) && this.boxLobbyDoorCode == other.boxLobbyDoorCode);
    }
}