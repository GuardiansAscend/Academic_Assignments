// -------------------------------------------------------
// Assignment 3
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This is a program that solves the locker puzzle game. It displays which lockers
remain open after all the students in the problem have passed, then ends the program.
*/
public class lockerPuzzle {
    
    public static void main(String[] args) {
        
        //This is the welcome message
        System.out.println("Hello, this is the solution to the locker puzzle, after"
                + " all 100 students have gone through:");
        
        //A boolean array that represents the lockers, initally all lockers are 
        //closed (all values are false).
        boolean [] lockers = new boolean[100];
        
        //This nested loop sends a student through the locker rows. Each int i is
        //an individual student.
        for (int i = 1; i < 101; i++) {
            for (int j = 0; (i + j - 1) < 100; j += i) {
                lockers[i + j - 1] = !lockers[i + j - 1];
            }
        }
        
        //This displays which lockers are open after all students have passed.
        for (int i = 0; i < 100; i++) {
            if(lockers[i])
                System.out.println("Locker " + (i + 1) + " is open");
        }
        
        //This is the closing message
        System.out.print("That is all the open lockers. Now closing the program.");
    }
}
