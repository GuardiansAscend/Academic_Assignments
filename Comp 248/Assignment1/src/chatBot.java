/**
 * Assignment 1
 * Written by: Kevin Marnet Scanlan, 40175644
 * For COMP 248 Section S 2202 - Fall 2020
 */

/*
This program pretends to be a chat bot, and starts by prompting the user for their name.
It then outputs the name capitalized, and the number of characters in the name.
It then prompts the user for a city name, and when it gets a valid city name, it
outputs the city name with the center letter(s) capitalized, then again with the
second letter capitalized, and finally with the second to last letter capitalized.
*/
import java.util.Scanner;
public class chatBot {
    
    public static void main(String[] args) {
        
        //This is the Welcome Message.
        System.out.println("------------------------CHAT BOT 1000------------------------");
        System.out.print("> Hello user. My name is CHAT BOT 1000. What is your name? ");
        
        //This is where the user's inputted name is initiated.
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();
        
        //This is the responded output displaying info about the user's name.
        System.out.println("> Why hello " + userName.toUpperCase() + ", pleasure to meet you.");
        System.out.println("> Did you know your name has " + userName.length() + " character(s) in it? Very interesting...");
        System.out.println("> Well, " + userName.toUpperCase() + ", care to tell me which city you live in?");
        System.out.print("> Oh, and don't bother entering a city name with less than 3 characters, "
                + "it won't do you any good. ");
        
        //This is a loop that keeps reprompting the user to enter a valid string, if they enter an invalid one.
        //Once the entered string is valid, it instiates it as the city name.
        String cityName;
        while (true) {            
            cityName = scanner.next();
            if(cityName.length() >= 3) {
                break;
            }
            System.out.print("> What did I just say? Please cooperate and enter a valid name. ");
        }
        
        //This checks if the city name has an odd or even amount of characters.
        //If odd, it only capitalizes the middle character. If even, it capitalizes
        //both center characters. It returns the string with the capitalized center
        //letter(s) in the output.
        if(cityName.length() % 2 == 1) {
            String centerLetters = cityName.substring((cityName.length() / 2), (cityName.length() / 2) + 1);
            System.out.println("> Lets try capitalizing some letters... Here is the middle upper cased: " + 
                cityName.substring(0, (cityName.length() / 2)).toLowerCase() + centerLetters.toUpperCase() + 
                    cityName.substring((cityName.length() / 2) + 1).toLowerCase());
        }
        else {
            String centerLetters = cityName.substring((cityName.length() / 2) - 1, (cityName.length() / 2) + 1);
            System.out.println("> Lets try capitalizing some letters... Here is the middle upper cased: " + 
                cityName.substring(0, (cityName.length() / 2) - 1).toLowerCase() + centerLetters.toUpperCase() + 
                    cityName.substring((cityName.length() / 2) + 1).toLowerCase());
        }
        
        //This capitalizes only the second letter in the city name, and outputs it to the user.
        System.out.println("> That was fun... Now the second letter is capitalized: " + cityName.substring(0, 1).toLowerCase() +
                cityName.substring(1, 2).toUpperCase() + cityName.substring(2).toLowerCase());
        
        //This capitalizes only the second to last letter in the city name, and outputs it to the user.
        System.out.println("> Last but not least, let's capitalize the before last letter: " + 
                cityName.substring(0, cityName.length() - 2).toLowerCase() + 
                    cityName.substring(cityName.length() - 2, cityName.length() - 1).toUpperCase() + 
                        cityName.substring(cityName.length() - 1));
        
        //This is the Closing Message.
        System.out.println("> It seems we have run out of time here, I must go now. Farewell, " + userName.toUpperCase());
    }
}
