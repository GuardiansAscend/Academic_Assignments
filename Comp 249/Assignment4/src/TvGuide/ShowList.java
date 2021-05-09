// -----------------------------------------------------
// Part: 2
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package TvGuide;

import java.util.NoSuchElementException;

/**
 * Linked list class, that contains all the TV shows in the TV guide.
 * @author Kevin Marnet Scanlan
 */
public class ShowList {

    /**
     * Inner class to represent a Node of the ShowList
     */
    public class ShowNode implements Cloneable{
         private TVshow show;
         private ShowNode next;

         /**
          * Default constructor, set both show and next to null
          */
         public ShowNode() {
            show = null;
            next = null;
        }

         /**
          * Parameterized constructor, accepts input for the TVshow and next Node,
          * and sets the fields to those.
          * @param show The TV show
          * @param next The next ShowNode
          */
         public ShowNode(TVshow show, ShowNode next) {
            this.show = show;
            this.next = next;
        }
        
         /**
          * Copy Constructor that takes a ShowNode, and creates an identical one
          * @param node The node to be copied
          */
         public ShowNode(ShowNode node) {
             this.show = new TVshow(node.show, node.show.getShowID());
             this.next = node.next;
        }
        
         /**
          * The clone method, copies the instance object.
          * @return the clone version of the ShowNode
          */
        @Override
        public ShowNode clone() {
            return new ShowNode(this);
        }

         /**
          * Getter method for the TVshow
          * @return the TV show
          */
         public TVshow getShow() {
            return show;
        }

         /**
          * Setter method for the TV show
          * @param show the new TV show
          */
         public void setShow(TVshow show) {
            this.show = show;
        }

        /**
         * Getter method for the next node
         * @return The next ShowNode
         */
        public ShowNode getNext() {
            return next;
        }

         /**
          * Setter method for the next node
          * @param next The new next ShowNode
          */
         public void setNext(ShowNode next) {
            this.next = next;
        }
     }
     
    private ShowNode head;
    private int size;
    
    /**
     * Default ShowList constructor, creates an empty List.
     */
    public ShowList() {
        head = null;
        size = 0;
    }
    
    /**
     * Copy Constructor, creates a deep copy of the passed ShowList.
     * @param list The ShowList to be copied
     */
    public ShowList(ShowList list) {
        ShowNode temp = list.head;
        ShowNode newHead =  null;
        ShowNode end = null;
        
        newHead = new ShowNode(temp.show.clone(temp.show.getShowID()), null);
        end = newHead;
        temp = temp.next;
        while(temp != null){
            end.next = new ShowNode(temp.show.clone(temp.show.getShowID()), null);
            
            end = end.next;
            temp = temp.next;
        }
        
        this.head = newHead;
        this.size = list.size;
    }
    
    /**
     * Method to add a node to the start of the list
     * @param show The show inside the node to be added
     */
    public void addToStart(TVshow show) {
        head = new ShowNode(show, head);
        this.size++;
    }
    
    /**
     * Method to insert a node at a certain index of the list
     * @param show the show inside the node to be added
     * @param index the index where the node will be added
     * @throws NoSuchElementException when the index is invalid
     */
    public void insertAtIndex(TVshow show, int index) throws NoSuchElementException{
        if(index < 0 || index > this.size)
            throw new NoSuchElementException("The given index was invalid, show " + show + " was not inserted.");
        
        if (index == 0) {
            addToStart(show);
        }
        else {
            ShowNode temp = head;
            while(index != 1) {
                temp = temp.next;
                index--;
            }
            ShowNode newNode = new ShowNode(show, temp.next);
            temp.setNext(newNode);
        }
        
        this.size++;
    }
    
    /**
     * Method to remove a node from the start of the list.
     * @throws NoSuchElementException when the list is empty
     */
    public void deleteFromStart() throws NoSuchElementException {
        if(this.size == 0)
            throw new NoSuchElementException("The ShowList is empty, cannot delete from the list.");
        
        this.head = this.head.next;
        this.size--;
    }
    
    /**
     * Method to remove a node from a given index of the list.
     * @param index index at which to remove the node
     * @throws NoSuchElementException when the index given is invalid
     */
    public void deleteFromIndex(int index) throws NoSuchElementException {
        if(index < 0 || index > this.size - 1)
            throw new NoSuchElementException("The given index was invalid, index " + index +  " was not deleted.");
        
        if(index == 0)
            deleteFromStart();
        
        else{
            ShowNode temp = head;
            while(index != 1) {
                temp = temp.next;
                index--;
            }
            
            ShowNode node = temp.next.next;
            temp.setNext(node);
        }
        
        this.size--;
    }
    
    /**
     * Method to replace a show inside the node at a given index of the list.
     * @param show The TV show that is taking the old one's place
     * @param index The index of the TV show to be replaced
     * @throws NoSuchElementException when the index is invalid
     */
    public void replaceAtIndex(TVshow show, int index) throws NoSuchElementException {
        if(index < 0 || index > this.size - 1)
            throw new NoSuchElementException("The given index was invalid, the show at index " + index +  " was not replaced.");
        
        ShowNode temp = head;
        while(index != 0) {
                temp = temp.next;
                index--;
            }
        
        temp.setShow(show);
    }
    
    //Since the find() method is returning a reference to a ShowNode, there is potential
    //for a privacy leak here, and access to the ShowNode's private variables is likely compromised. 
    //A way to fix this could be to make the inner ShowNode class private, which would require
    //a change in the construction of these methods.

    /**
     * Method that finds a given show Id in the list and returns the reference to
     * the ShowNode in question if it is present.
     * @param id The show Id we are looking for
     * @return The reference to the ShowNode with this ID, or null if it isn't present in the list
     */
    public ShowNode find(String id) {
        ShowNode temp = head;
        int iteration = 0;
        while(temp != null) {
            if(temp.show.getShowID().equals(id)) {
                System.out.println("It took " + iteration + " iterations to find the show you are looking for.");
                return temp;
            }
            
            temp = temp.next;
            iteration++;
        }
        
        System.out.println("The method performed " + iteration + " iterations, but the show you are looking for is not present in the list.");
        return temp;
    }
    
    /**
     * Method that checks whether the list contains a certain ID or not
     * @param id The show ID we are searching for
     * @return true if the list has that ID, false otherwise
     */
    public boolean contains(String id) {
        return (find(id) != null);
    }
    
    /**
     * Method that checks if an equivalent TV show is already present in the list
     * (Overload of the previous method, used to find duplicate entries in the 
     * TVGuide file)
     * @param show The TV show we are checking for
     * @return true if it is present in the list, false otherwise
     */
    public boolean contains(TVshow show) {
        ShowNode temp = head;
        while(temp != null) {
            if(temp.show.equals(show)) {
                return true;
            }
            temp = temp.next;
        }
        
        return false;
    }
    
    /**
     * Method that compares 2 lists, and checks whether the lists are equivalent
     * (except for Show IDs). 2 equivalent lists must have the same amount of shows,
     * in the same order, and with all data parameters identical with the exception
     * of Show IDs
     * @param other The show list we are comparing to
     * @return whether the lists are equivalent or not
     */
    public boolean equals(ShowList other) {
        if(this.size != other.size)
            return false;
        
        ShowNode tempThis = this.head;
        ShowNode tempOther = other.head;
        
        while(tempThis != null && tempOther != null) {
            if(!tempThis.getShow().equals(tempOther.getShow()))
                return false;
            
            tempThis = tempThis.next;
            tempOther = tempOther.next;
        }
         return true;
    }

    /**
     * ToString method for a ShowList
     * @return A string version of the showList, with all its shows inside it
     */
    @Override
    public String toString() {
        String output = "ShowList: ";
        ShowNode temp = head;
        while (temp != null) {
            output += temp.getShow() + ", ";
            temp = temp.next;
        }
        
        return output.substring(0, output.length() - 2);
    }
    
}
