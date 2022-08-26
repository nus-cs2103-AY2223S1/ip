package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String DIVIDER = "____________________________________________________";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";


    /**
     * Prints loading error if data/duke.txt does not exist.
     */
    public static void showLoadingError() {

    }

    /**
     * Prints welcome message when Duke is initialized.
     */
    public static void showWelcomeMessage() {
        System.out.println(String.format("%s\n%s\n%s", DIVIDER, INTRO, DIVIDER));
    }

    /**
     * Prints goodbye message when user inputs "bye".
     */
    public static void showGoodbyeMessage() {
        System.out.println(String.format("%s\n%s\n%s", DIVIDER, GOODBYE, DIVIDER));
    }

    /**
     * Returns user command based on user input.
     * @param input user input.
     * @return command (usually first word of input).
     */
    public static String getUserCommand(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        return command;
    }

}
