// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package CommAddress;
import SimpleAddress.Address;

/**
 * A class that represents an email address.
 * @author Kevin Marnet Scanlan
 */
public class EmailAddress extends Address{
    
    /**
     * The username of the email address.
     */
    private String userName;
    
    /**
     * The domain name of the email address.
     */
    private String domainName;
    
    /**
     * The top-level domain of the email address.
     */
    private String TLD;

    /**
     * Default constructor for an EmailAddress, sets the username, domain name and
     * TLD to placeholder values.
     */
    public EmailAddress() {
        super();
        userName = "userName";
        domainName = "domainName";
        TLD = "TLD";
    }

    /**
     * Parameterized constructor for an EmailAddress, which creates an EmailAddress
     * with the inputted parameters.
     * @param userName The username of the owner of the address
     * @param domainName The domain name of the email
     * @param TLD The top-level domain of the email
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public EmailAddress(String userName, String domainName, String TLD, String validFrom, String validTo) {
        super(validFrom, validTo);
        this.userName = userName;
        this.domainName = domainName;
        this.TLD = TLD;
    }

    /**
     * Copy constructor for an EmailAddress, which creates a new EmailAddress
     * with the same parameters as the passed Email Address.
     * @param original The EmailAddress to be copied
     */
    public EmailAddress(EmailAddress original) {
        this(original.userName, original.domainName, original.TLD, original.validFrom, original.validTo);
    }

    /**
     * Getter method for the username.
     * @return The value of the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for the username.
     * @param userName The new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for the domain name.
     * @return The value of the domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Setter method for the domain name.
     * @param domainName The new domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Getter method for the TLD.
     * @return The value of the TLD
     */
    public String getTLD() {
        return TLD;
    }

    /**
     * Setter method for the TLD.
     * @param TLD The new TLD
     */
    public void setTLD(String TLD) {
        this.TLD = TLD;
    }

    /**
     * toString method, returns the information of this EmailAddress.
     * @return The EmailAddress's info in String form
     */
    @Override
    public String toString() {
        return "This email address is " + userName + "@" + domainName + "." + TLD + 
            "\n" + super.toString();
    }
    
    /**
     * equals method, compares whether the EmailAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        EmailAddress other = (EmailAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.userName.equals(other.userName) && this.domainName.equals(other.domainName)
                && this.TLD.equals(other.TLD));
    }
}
