package TreasureHunt;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

/*
•Create a two-dimensional array of integers (the size is your choice)
•In random positions in the array store a random amount of coins
•Get the user to enter coordinates where they think the treasure is
•If there is treasure at this point this gets added to the users total

Stretch and Challenge
•Make this a 2 player game
•Add a feature to say ‘hot’ ‘cold’ ‘warm’ depending on how close their guess was to the hidden treasure
•Add monsters and bonus loot squares

Tough Stretch and Challenge
•Make the monsters objects
•The monsters can move around in the game and eat the treasure
•Monsters can also steal all of your treasure and end the game if you land on a monster or if they land on you
*/


    private static String[][] map = new String[10][10];
    private static int[][] treasureMap = new int[10][10];
    private static int x;
    private static int y;
    private static int score = 0;

    public static void skipLines() {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            //This prints a bunch of blank lines to clear the console and make everything look a bit more clean, I originally wanted to clear the console but i couldn't find a way to do so
        }
    }

    public static void createMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = ("[ ]");
                //creates the map by populating the 10*10 2D String array with empty square brackets, This is what will be used to print the board and mark locations that have been searched
            }
        }
    }

    public static void printMap() {
        System.out.print("   ");
        for(int b = 0; b < 10; b++){
            System.out.print(" "+(b+1) + " "); //column number
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if((i+1)<10){
                System.out.print((i+1) + "  ");
            }else{
                System.out.print((i+1) + " ");
            }
                // row number
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
                // a method to print the board, by using this method i can print the board in one line, this helps keep my for loop in my main method clutter free which will help me in debugging if i run into issues
            }
            System.out.println();
            //moves onto the next row
        }
    }

    public static void treasureMaker() {
        Random randint = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp = randint.nextInt(100) + 1;
                if (temp < 50) {
                    treasureMap[i][j] = randint.nextInt(400) + 100;
                } else {
                    treasureMap[i][j] = 0;
                }
            }   // This is basically the cheat map used by the computer that creates a second variant of our map that we print but this is an Integer array that stores a random amount of loot or -1.
        }
    }

    public static void userGuessLocation() {
        while (true) {
            try {
                Scanner scanInput = new Scanner(System.in);
                System.out.print("Please enter your x coordinate - ");
                x = scanInput.nextInt();

                System.out.print("PLease enter your y coordinate- ");
                y = scanInput.nextInt();
                if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
                    break;
                } else {
                    System.out.println("Parameters should be between 0-9");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e + "\n");
            }
        } //This method basically takes the input from the user and stores it as y and x the global variables.
    }

    public static int guessChecker(int x, int y){
        //System.out.println(map[x][y]);
        if(map[x][y].contains("[X]")){
            return 1;
        }else{
            map[x][y] = ("[X]");
            return 0; //work in progress :( (was very buggy and couldn't get it to work consistently)
        }
    }

    public static void checkLocation(int x, int y) {
        if (map[x][y].equals("[ ]")) {
            if (treasureMap[x][y] != 0) {
                map[x][y] = "[X]";
                System.out.println("You have found " + treasureMap[x][y]);
                score += treasureMap[x][y];
                System.out.println("Total Score is now " + score);

            } else {
                map[x][y] = "[X]";
                System.out.println("How unlucky of you to find nothing");
            }
        } else {
            System.out.println("You have already searched this location!! ");
        }  //This checks whether the location chosen(takes in the x and y coordinates as input) is searched or hasn't been searched and then if it hasn't been searched it sees if there is any loot associated with that position in the treasureMap
    }

    public static void printAndPublishScore() {
        System.out.println("You have finished the game with the score of " + score);
//        Scanner scan = new Scanner(System.in);
//        try{
//            System.out.print("Do you want to save this score?(y/n)\t");
//            char confirmation = scan.next().charAt(0);
//            if(confirmation==('y')){
//                System.out.print("What is your nickname - ");
//            }else{
//                System.out.println("Thanks for playing!");
//            }
//
//        }catch(Exception e){
//            System.out.println("Error: " + e);
//        }

        //Will write to text file
    }

    public static void main(String[] args) {
        createMap();
        treasureMaker();
        //int guess = 10;
        try {
            for (int i = 10; i > 0; i--) {
                //System.out.println(i);
                printMap();
                userGuessLocation();
                checkLocation(x, y);
                System.out.println("You have " + (i-1) + " guesses left!");
                //guess = i - guessChecker(x, y);
                //System.out.println(guessChecker(x, y));
                //System.out.println(guess);
                TimeUnit.SECONDS.sleep(3);
                skipLines();
            }
        } catch (Exception e) {
            System.out.println("Oopsie daisy, something has gone wrong, give code " + e + " to the support team, Thank you");
        }
        printAndPublishScore();



    }
}
