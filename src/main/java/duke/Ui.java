package duke;

/**
 * deals with interactions with the user for Duke
 */
public class Ui {
    /**
     * greets the user on run
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * error when loading
     */
    public void showLoadingError() {
        System.out.println("Error loading");
    }

    /**
     * prints the message out for the user
     * @param msg message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * says bye to the user on bye command before termination
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
