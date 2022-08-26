package duke;

import java.util.Scanner;

/**
 * Class that deals with interactions with the user
 */
public class Ui {

    /**
     * Method that prints a hello message
     */
    public static void helloMessage() {
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
    }

    /**
     * Method that prints a bye message
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method that prints a loading error message
     */
    public static void showLoadingError() {
        System.out.println("Loading error...");
    }
}
