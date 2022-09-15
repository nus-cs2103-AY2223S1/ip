package seedu.deku;

import java.util.ArrayList;

/**
 * Represents the Ui for Deku.
 */
public class Ui {

    public static final String INTRO = "Hello! I'm Deku\nWhat can I do for you?";
    public static final String DIVIDER = "____________________________________________________";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";


    /**
     * Prints loading error if data/deku.txt does not exist.
     */
    public static void showLoadingError() {

    }

    /**
     * Prints welcome message when Deku is initialized.
     */
    public static String showWelcomeMessage() {
        return String.format("%s\n%s\n%s", DIVIDER, INTRO, DIVIDER);
    }

    /**
     * Prints goodbye message when user inputs "bye".
     */
    public static String showGoodbyeMessage() {
        return String.format("%s\n%s\n%s", DIVIDER, GOODBYE, DIVIDER);
    }

    /**
     * Returns user command based on user input.
     *
     * @param input user input.
     * @return command (usually first word of input).
     */
    public static String getUserCommand(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        return command;
    }

    public static String showMatchingTasks(ArrayList<Task> tasks) {
        String matchingTasks = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            matchingTasks += String.format("\n%s. %s", i + 1, tasks.get(i));
        }
        assert matchingTasks instanceof String;
        return matchingTasks;
    }
}
