package mco364;

import java.awt.Robot;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mco364.AppSettings.step;

public class Main {

    public static void main(String[] args) {

        boolean gameInPlay = true;
        Scanner kb = new Scanner(System.in);
        System.out.println("Choose a pattern: \nClick 1 for Blinker,"
                + "\nClick 1 for Blinker,"
                + "\nClick 2 for Toad,"
                + "\nClick 3 for Beacon,"
                + "\nClick 4 for Pulsar,"
                + "\nClick 5 for Pentdecalthlon");
        int patternChoice = kb.nextInt();
        System.out.println("Choose 1 for automation, and 2 for step by step");
        int userChoice = kb.nextInt();

        if (userChoice == 2) {
            AppSettings.IS_STEPbySTEP = true;
        }

        GameOfLife game = new GameOfLife(patternChoice);

        do {
            game.nextGen();
            game.printBoard();
            sleep(500);
            step();
            clearConsole();
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

    public static boolean IS_STEPbySTEP = false;
    static Scanner kb = new Scanner(System.in);

    public static void step() {
        if (IS_STEPbySTEP) {
            System.out.println("Hit Enter to Continue");
            kb.nextLine();
        }
    }
}
