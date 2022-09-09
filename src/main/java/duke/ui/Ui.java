package duke.ui;

import java.util.Scanner;

/**
 * A class handling the duke's interactions with the user.
 */
public final class Ui {

    /**
     * Returns the Welcome message.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Dook\n"+
                "What's up?";
    }

    /**
     * Returns the exit message.
     */
    public static String getByeMessage() {
        return "hehe bye";
    }

    /**
     * Prints an error message.
     *
     * @param msg The message of the error.
     */
    public static String getErrorMessage(String msg) {
        return "Something bad happened! :O\n" + msg;
    }
}
