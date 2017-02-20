package mco364;

import java.awt.Robot;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mco364.AppSettings.step;

public class Main {

    public static void main(String[] args) {
        //Code the user input...
        boolean gameInPlay = true;
//        Scanner kb = new Scanner(System.in);
//        PATTERN userChoice;
//        
//        System.out.println(Choose);

        GameOfLife game = new GameOfLife(PATTERN.PENTADECATHLON);

        do {
            game.nextGen();
            game.printBoard();
            sleep(500);
            step();
            clearConsole();
            //Check if game is still in play based on user input...
        } while (gameInPlay);

    }

    public final static void clearConsole() {
        for (int i = 0; i < 100; i++) { // safety net since next code only works on console not Netbeans output
            System.out.println();
        }
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            // ignore
        }
    }
}

class AppSettings {

    public final static boolean IS_STEPbySTEP = false;
    static Scanner kb = new Scanner(System.in);

    public static void step() {
        if (IS_STEPbySTEP) {
            System.out.println("Hit Enter to Continue");
            kb.nextLine();
        }
    }
}
