package drivers;

import java.util.Scanner;

/**
 * Deals with interactions from the user.
 */
public class Ui {
    /**
     * Greeting message to the user during chat-bot startup.
     */
    public String greeting() {
        return "Hi! I am Tumu. Nice to meet you!\n" +
                "\tWhat is on your mind today?\n";
    }

    /**
     * Where all the messages seen by the user will pass through.
     * @param message The message to be printed, after additional
     *                formatting.
     */
    public String notifyUser(String message) {
        return message + "\n";
    }
}
