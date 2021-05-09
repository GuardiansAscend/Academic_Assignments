// -----------------------------------------------------
// Part: 1 & 2
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
import SimpleAddress.Address;
import CommAddress.*;
import LocationAddress.*;
import java.util.Arrays;
/**
 * The driver class from which the class hierarchy is tested.
 * @author Kevin Marnet Scanlan
 */
public class Driver {
    
    /**
     * Static method which takes an array of addresses, copies each object into
     * another Address array using the Address copy constructor and returns the new
     * array. Note that this method will not function as intended.
     * @param originals The Address array to be copied
     * @return The new array of copied Address objects
     */
    public static Address[] copyAddresses(Address[] originals) {
        Address[] copiedAddresses = new Address[originals.length];
        for (int i = 0; i < copiedAddresses.length; i++) {
            copiedAddresses[i] = new Address(originals[i]);
        }
        return copiedAddresses;
    }
    
    /**
     * The main method.
     * @param args Arguments
     */
    public static void main(String[] args) {
        //emails
        EmailAddress email1 = new EmailAddress("BobbyFisch33", "lichess", "net", "1975-04-13"
            , "2009-10-14");
        System.out.println(email1);
        EmailAddress email2 = new EmailAddress("BigMoney$42069", "hotmail", "com", "2005-11-03"
            , "2030-01-29");
        System.out.println(email2);
        EmailAddress email3 = new EmailAddress(email1);
        System.out.println(email3 + "\n");
        
        //telecoms
        TelecomAddress tele1 = new TelecomAddress("+1", "(0)", 514, 6205941, 0, 
            "landline", "2007-05-17", "2025-06-20");
        System.out.println(tele1);
        TelecomAddress tele2 = new TelecomAddress("+4", "(6)", 666, 8572655, 717, 
            "mobile", "1609-09-11", "1991-08-06");
        System.out.println(tele2);
        TelecomAddress tele3 = new TelecomAddress("+2", "(1)", 559, 3948230, 12, 
            "fax", "2007-05-17", "2025-06-20");
        System.out.println(tele3);
        TelecomAddress tele4 = new TelecomAddress(tele2);
        System.out.println(tele4 + "\n");
        
        //webpages
        WebPageAddress web1 = new WebPageAddress("www.cornhub/juicypix", "2014-03-09", "2067-01-01");
        System.out.println(web1);
        WebPageAddress web2 = new WebPageAddress("www.reddit/ciasecrets", "2006-02-10", "2021-01-22");
        System.out.println(web2);
        WebPageAddress web3 = new WebPageAddress(web2);
        System.out.println(web3 + "\n");
        
        //locales
        Locale locale1 = new Locale("CA", 124, "Canada");
        Locale locale2 = new Locale("US", 840, "United States of America");
        Locale locale3 = new Locale("IN", 356, "Republic of India");
        Locale locale4 = new Locale("PD", 777, "Paradise Island");
        Locale locale5 = new Locale("NW", 505, "Land of Nowhere");
        
        //general deliveries
        GeneralDeliveryAddress genDeliv1 = new GeneralDeliveryAddress(tele1, "1600 rue Atwater",
            "Montreal", "QC", "T3B 6H6", locale1, "2001-12-30", "2016-02-29");
        System.out.println(genDeliv1);
        GeneralDeliveryAddress genDeliv2 = new GeneralDeliveryAddress(tele2, "696 Endoff Rd.",
            "Rapture", "The Pits", "TP3600", locale5, "1700-07-07", "3001-03-31");
        System.out.println(genDeliv2);
        GeneralDeliveryAddress genDeliv3 = new GeneralDeliveryAddress(genDeliv1);
        System.out.println(genDeliv3 + "\n");
        
        //geographics
        GeographicAddress geoAddr1 = new GeographicAddress("505 Sugar Cane Lane", "Kansas City",
                "KS", "64106", locale2, "2224-07-12", "2224-07-13");
        System.out.println(geoAddr1);
        GeographicAddress geoAddr2 = new GeographicAddress("432 Uno Blvd.", "New Delhi",
                "SL", "673422", locale3, "2018-05-22", "2024-09-16");
        System.out.println(geoAddr2);
        GeographicAddress geoAddr3 = new GeographicAddress("202 rue Sammerset", "Dallord-des-Armeoux",
                "QC", "G9H 2H8", locale1, "2020-03-07", "2021-03-08");
        System.out.println(geoAddr3);
        GeographicAddress geoAddr4 = new GeographicAddress(geoAddr2);
        System.out.println(geoAddr4 + "\n");
        
        //PO boxes
        PostOfficeBoxAddress poBox1 = new PostOfficeBoxAddress(29301, "234 Baker Street", 
                "Fort Knox", "AL", "33295", locale2, "2225-11-07", "2318-05-29");
        System.out.println(poBox1);
        PostOfficeBoxAddress poBox2 = new PostOfficeBoxAddress(4004135, "888 Rainbow Road", 
                "Limbo", "Andromeda", "2FA2FU", locale4, "1111-11-11", "2222-02-22");
        System.out.println(poBox2);
        PostOfficeBoxAddress poBox3 = new PostOfficeBoxAddress(poBox1);
        System.out.println(poBox3 + "\n");
        
        //equals testing
        System.out.println("The statement \"poBox1 is equivalent to pobox3\" is " +
            poBox1.equals(poBox3));
        System.out.println("The statement \"poBox2 is equivalent to pobox3\" is " +
            poBox2.equals(poBox3));
        System.out.println("The statement \"web1 is equivalent to web3\" is " +
            web1.equals(web3));
        System.out.println("The statement \"genDeliv1 is equivalent to genDeliv2\" is " +
            genDeliv1.equals(genDeliv2));
        System.out.println("The statement \"tele2 is equivalent to tele4\" is " +
            tele2.equals(tele4));
        System.out.println("The statement \"email2 is equivalent to email3\" is " +
            email2.equals(email3) + "\n");
        
        //array creation and obsolete tracing
        Address[] addressList = {email1, email2, tele1, tele3, tele4, web1, web3,
            genDeliv1, genDeliv2, geoAddr1, geoAddr2, geoAddr3, geoAddr4, poBox1, poBox2};
        traceObsoleteAddresses(addressList, 2021, 3, 8);
        
        //array copying attempt
        Address[] copiedAddresses = copyAddresses(addressList);
        System.out.println("Here is the orginal array of addresses: " + Arrays.toString(addressList) + "\n");
        System.out.println("Here is the copied array of addresses: " + Arrays.toString(copiedAddresses) + "\n");
        System.out.println("Copy unsuccesful, all objects became of Address type.");
        
        //Notice that the copied array is filled with objects of the Address class, and
        //not of the subclasses of Address. This is expected, because I called the copy
        //constructor of the Address class, and the Address class cannot see the 
        //copy constructors of its children, because polymorphism does not function
        //in this way. As such, the copied subclasses all became the type of the 
        //super class, losing their more specific fields.
        System.out.println("This is the end of the program.");
    }
    
    /**
     * Static method which takes an Address array and a date, and then checks for 
     * obsolete addresses in the array. If it finds one, it prints the index of 
     * the Address and its information in the console.
     * @param addresses The address array to be checked
     * @param year The year to be compared to
     * @param month The month to be compared to
     * @param day The day to be compared to
     */
    public static void traceObsoleteAddresses(Address[] addresses, int year, int month, int day) {
        for(int i = 0; i < addresses.length; i++) {
            if(addresses[i].isObsolete(year, month, day)) {
                System.out.println("The Address at index " + i + " of the array is obsolete:");
                System.out.println(addresses[i] + "\n");
            }
        }
    }
}