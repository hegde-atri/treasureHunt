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
        }
    }

    public static void createMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = ("[ ]");
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
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
            }
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
        }
    }

    public static void checkLocation(int x, int y) {
        if (map[x][y].contains("[ ]")) {
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
        }
    }

    public static void printAndPublishScore() {
        System.out.println("You have finished the game with the score of " + score);
    }

    public static void main(String[] args) {
        createMap();
        treasureMaker();
        try {
            for (int i = 0; i < 10; i++) {
                printMap();
                userGuessLocation();
                checkLocation(x, y);
                TimeUnit.SECONDS.sleep(1);
                skipLines();
            }
        } catch (Exception e) {
            System.out.println("Oopsie daisy, something has gone wrong, give code " + e + " to the support team, Thank you");
        }
        printAndPublishScore();


    }
}
