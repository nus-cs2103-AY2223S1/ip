package ren;

import java.util.Scanner;

/**
 * Ui handles all interactions with the user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a message with message borders.
     *
     * @param message Message to print.
     */
    public void speak(String message) {
        System.out.println("=======================================================================================\n");
        System.out.println(message);
        System.out.println("=======================================================================================");
    }

    /**
     * Greets the user.
     */
    public void greet() {
        speak(" Greetings! My name is Ren ^_^\n How may I be of service today?\n");
    }

    /**
     * Returns a Farewell message for the user.
     *
     * @return String with the Farewell message.
     */
    public static String goodbye() {
        return " Farewell!\n";
    }

    /**
     * Reads a command from the user.
     *
     * @return String with the command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
