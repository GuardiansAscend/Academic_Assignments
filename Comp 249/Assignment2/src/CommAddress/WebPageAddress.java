// -----------------------------------------------------
// Part: 1
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package CommAddress;
import SimpleAddress.Address;

/**
 * A class that represents a web page address.
 * @author Kevin Marnet Scanlan
 */
public class WebPageAddress extends Address{
    
    /**
     * The URL of the web page.
     */
    private String URL;

    /**
     * Default constructor for a WebPageAddress, sets the URL to a placeholder value.
     */
    public WebPageAddress() {
        super();
        URL = "www.domainName/resourceName";
    }

    /**
     * Parameterized constructor for a WebPageAddress, which creates a WebPageAddress
     * with the inputted parameters.
     * @param URL The URL of the web page
     * @param validFrom The first validity date
     * @param validTo The last validity date
     */
    public WebPageAddress(String URL, String validFrom, String validTo) {
        super(validFrom, validTo);
        this.URL = URL;
    }

    /**
     * Copy constructor for a WebPageAddress, which creates a new WebPageAddress
     * with the same parameters as the passed WebPageAddress.
     * @param original The WebPageAddress to be copied
     */
    public WebPageAddress(WebPageAddress original) {
        this(original.URL, original.validFrom, original.validTo);
    }

    /**
     * Getter method for the URL.
     * @return The value of the URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * Setter method for the URL.
     * @param URL The new URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * toString method, returns the information of this WebPageAddress.
     * @return The WebPageAddress's info in String form
     */
    @Override
    public String toString() {
        return "This webpage address is " + URL + "\n" + super.toString();
    }
    
    /**
     * equals method, compares whether the WebPageAddress is identical to the passed object.
     * @param obj The object to be compared to
     * @return True if they are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        
        WebPageAddress other = (WebPageAddress) obj;
        return (this.validFrom.equals(other.validFrom) && this.validTo.equals(other.validTo)
            && this.URL.equals(other.URL));
    }
}
