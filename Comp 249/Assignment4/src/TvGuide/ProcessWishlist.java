// -----------------------------------------------------
// Part: 2
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package TvGuide;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the test class, where all the linked list objects and TV shows are used
 * to see if they work properly.
 * @author Kevin Marnet Scanlan
 */
public class ProcessWishlist {
    
    /**
     * Main method, where the files are read, the user is prompted and all the testing is done.
     * @param args
     */
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        ShowList list1 = new ShowList();
        
        System.out.println("Hello, and Welcome to your sentient TV guide!");
        System.out.println("Today, I will be checking over your wishlidt, to see if you can watch any new shows!");
        System.out.println("Please enter the name of your txt file that contains the TV guide " +
                 "(Make sure your file is in the same directory as your source code).");
         System.out.print("Enter it here: ");
         
         String guideFileName = consoleScanner.next();
         if(guideFileName.length() < 4 || !guideFileName.substring(guideFileName.length() - 4).equals(".txt")) 
             guideFileName += ".txt";
         System.out.println("");
         
          System.out.println("Excellent! Now please enter the name of your txt file that contains your interests " +
                 "(Make sure your file is in the same directory as your source code).");
         System.out.print("Enter it here: ");
         
         String interestsFileName = consoleScanner.next();
         if(interestsFileName.length() < 4 || !interestsFileName.substring(interestsFileName.length() - 4).equals(".txt")) 
             interestsFileName += ".txt";
         System.out.println("");
        
        Scanner guideScanner = null;
        Scanner interestsScanner = null;
        ArrayList<TVshow> watchList = new ArrayList<>();
        ArrayList<TVshow> wishList =  new ArrayList<>();
        int counter = 0;
        try {
            guideScanner = new Scanner(new FileInputStream("src/TvGuide/" + guideFileName));
            
            //reading the TVguide file directly into the list
            while (guideScanner.hasNextLine()) {
                String showIDStr = guideScanner.next();
                String showNameStr = guideScanner.next();
                guideScanner.nextLine();
                String startStr = guideScanner.nextLine();
                String endStr = guideScanner.nextLine();
                
                double startTime = Double.parseDouble(startStr.substring(2));
                double endTime = Double.parseDouble(endStr.substring(2));
                TVshow newShow = new TVshow(showIDStr, showNameStr, startTime, endTime);
                if(!list1.contains(newShow))
                    list1.insertAtIndex(newShow, counter++);
            }
            guideScanner.close();
            
            interestsScanner = new Scanner(new FileInputStream("src/TvGuide/" + interestsFileName));
            interestsScanner.nextLine();
            //reading the Interst file into 2 seperate ArrayLists
            String nextID = interestsScanner.nextLine();
            while(!nextID.equalsIgnoreCase("Wishlist")) {
                ShowList.ShowNode watchedShowNode = list1.find(nextID);
                if(watchedShowNode != null)
                    watchList.add(watchedShowNode.getShow());
                nextID = interestsScanner.nextLine();
            }
            
            nextID = interestsScanner.nextLine();
            while(!nextID.equals("")) {
                ShowList.ShowNode wishedShowNode = list1.find(nextID);
                if(wishedShowNode != null)
                    wishList.add(wishedShowNode.getShow());
                if(interestsScanner.hasNextLine())
                    nextID = interestsScanner.nextLine();
            }
            interestsScanner.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("File(s) could not be found. Please make sure your files are next to the source code.");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.err.println("TVguide.txt is incorrectly formatted. Please make sure you follow the convention.");
            System.exit(0);
        }
        //The time frame comparison of the wishlist shows and watched shows
        for (TVshow show : wishList) {
            checkForShowSpace(watchList, show);
        }
        
        System.out.println("\nGreat! That was all the information you had on your Interests list.");
        System.out.println("Now, feel free to enter a few show IDs, to see if we have it in our system.");
        System.out.print("When you are done entering IDs, write \"done\": ");
        //prompting the user to input some Show IDs, as many times as they want
        String nextID = consoleScanner.next();
        while(!nextID.equalsIgnoreCase("done")){
            ShowList.ShowNode node = list1.find(nextID);
            if(node != null) {
                System.out.println("\n" + node.getShow());
            }
            System.out.println("");
            System.out.print("If you want stop inputting IDs, write \"done\", otherwise write another ID here: ");
            nextID = consoleScanner.next();
        }
        
        //Testing my methods
        System.out.println("OK, we are all done. Now time for me to test some methods.");
        TVshow tv1 = new TVshow("PBS12", "Blues_Clues", 8.3, 9.0);
        TVshow tv2 = new TVshow("ABC21", "Ancient_Family", 23.0, 24.0);
        TVshow tv3 = new TVshow(tv1, "PBSX12");
        TVshow tv4 = tv2.clone("ABC2100");
        
        System.out.println("Here are some TV shows:");
        System.out.println(tv1);
        System.out.println(tv2);
        System.out.println(tv3);
        System.out.println(tv4);
        System.out.println("\n");
        
        
        System.out.println("Here are some Linked Lists of TV shows:");
        ShowList list2 = new ShowList(list1);
        System.out.println(list1);
        System.out.println(list2);
        list2.addToStart(tv1);
        System.out.println(list2);
        list2.insertAtIndex(tv3, 0);
        System.out.println(list2);
        list2.deleteFromStart();
        System.out.println(list2);
        list2.deleteFromIndex(0);
        System.out.println(list2);
        list2.insertAtIndex(tv2, 4);
        System.out.println(list2);
        list2.deleteFromIndex(4);
        System.out.println(list2);
        
        System.out.println("\nTesting contains and replaceAt:");
        list2.replaceAtIndex(tv4, 2);
        System.out.println(list2.contains("ABC2100"));
        System.out.println(list2.contains("gibberish"));
        
        System.out.println("\nTesting the copy constructor more, and the equals method");
        ShowList list3 = new ShowList(list1);
        System.out.println("list1 is the same as list3: " + list3.equals(list1));
        System.out.println("list2 is the same as list3: " + list3.equals(list2));
        
        System.out.println("\nAlright, that seems to be it. Thank you for using the sentient TV guide!");
    }
    
    /**
     * Method that checks for whether a Wishlist show can be watched or not, compared to a list
     * of already watched shows.
     * @param watchlist The list of watched shows
     * @param wishedShow The show the user wishes to watch
     */
    public static void checkForShowSpace(ArrayList<TVshow> watchlist, TVshow wishedShow) {
        for (TVshow watchedShow : watchlist) {
            String timeCompare = watchedShow.isOnSameTime(wishedShow);
            
            if(timeCompare.startsWith("Same")){
                System.out.println("User cannot watch show " + wishedShow.getShowID() + ", as he/she will begin another show at the same time.");
                return;
            }
            if(timeCompare.startsWith("Some")){
                System.out.println("User cannot watch show " + wishedShow.getShowID() + ", as a show he/she is watching has some overlap with this show.");
                return;
            }
        }
        
        System.out.println("User can watch show " + wishedShow.getShowID() + ", as he/she is not watching anything during that time.");
    }
}
