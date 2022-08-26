package duke;

/**
 * Deals with interactions with the user for Duke.
 */
public class Ui {
    /**
     * Greets the user on run
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading");
    }

    /**
     * Prints the message out for the user.
     *
     * @param msg message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints bye to the user on bye command before termination.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
