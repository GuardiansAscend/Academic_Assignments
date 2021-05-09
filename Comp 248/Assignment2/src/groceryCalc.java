// -------------------------------------------------------
// Assignment 2
// Written by: Kevin Marnet Scanlan 40175644
// For COMP 248 Section S – Fall 2020
// --------------------------------------------------------

/*
This program takes a list of quantities of products on a list, and then calculates
their price according to certain discount and rewards rules (see assignment instructions
for details), then outputs the total price and earned rewards points if applicable.
*/
import java.util.Scanner;
public class groceryCalc {
    //These are the prices for each type of product, instiated as final because
    //they should not change.
    public static final double FRUIT_PRICE = 26.99;
    public static final double CHEESE_PRICE = 22.99;
    public static final double DAIRY_PRICE = 13.99;
    public static final double MEAT_PRICE = 56.99;
    public static final double SEAFOOD_PRICE = 38.99;
    //This is the rewards multiplier, by which we multiply the price to get the rewards
    //points total.
    public static int pointsMultiplier;
    
    public static void main(String[] args) {
        //This is the welcome and prompt messages
        System.out.println("-------------------------");
        System.out.println("    Shop-o-matic 2000    ");
        System.out.println("-------------------------");
        System.out.println("Welcome to ShopMart, where the customer is king!\n");
        System.out.println("Please enter the quantities of produce that you would"
            + " like to purchase,");
        System.out.print("In the order of ShopMart's produce list: ");
        
        //Here we initiate the Scanner, and take in each value provided by the user.
        Scanner scanner =  new Scanner(System.in);
        int fruit = scanner.nextInt();
        int cheese = scanner.nextInt();
        int dairy = scanner.nextInt();
        int meat = scanner.nextInt();
        int seafood = scanner.nextInt();
        
        //Here we calculate the amount the user will need to pay, and store the value.
        double totalPrice = ((discountRewardsCalc(fruit, cheese, dairy, meat))*
                (fruit*FRUIT_PRICE + cheese*CHEESE_PRICE + dairy*DAIRY_PRICE + meat*MEAT_PRICE)) 
                    + seafood*SEAFOOD_PRICE;
        
        //Here we will prompt the user for their membership.
        System.out.print("Are you a ShopMartRewards member? (Enter \"Y\" for yes, and anything else for no): ");
        
        //Here we will calculate the rewards points if the user is a member, and 
        //output the requested results.
        String resultsString = String.format("The total price is %.2f$.", totalPrice);
        if(scanner.next().equalsIgnoreCase("y")){
            int pointTotal = (int) Math.round(totalPrice*pointsMultiplier);
            resultsString += " You will receive " + pointTotal + " points.";
        }
        System.out.println(resultsString);
        
        //This is the closing message
        System.out.println("\nThat wraps it up! Thank you for shopping at ShopMart!");
    }
    
    //This method calculates the amount of the total price that needs to be paid, 
    //according to the discount rules of the flyer. It also sets the points multiplier,
    //for if the user is a member.
    public static double discountRewardsCalc(int fruit, int cheese, int dairy, int meat) {
        double totalNotSeafood = fruit*FRUIT_PRICE + cheese*CHEESE_PRICE + dairy*DAIRY_PRICE + meat*MEAT_PRICE;
        
        if(totalNotSeafood < 250){
            pointsMultiplier = 2;
            return 0.9;
        }
        else if(totalNotSeafood > 500) {
            pointsMultiplier = 3;
            return 0.8;
        }
        else {
            pointsMultiplier = 2;
            return 0.85;
        }
    }
}
