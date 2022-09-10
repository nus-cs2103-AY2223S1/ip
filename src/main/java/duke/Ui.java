package duke;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    /** Scanner to read input from the user */
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message when Duke is opened.
     */
    public static String greeting() {
        String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        return greetingMessage + "Here are some tasks that were left from before:\n";
    }

    /**
     * Prints the exit message when Duke is closed.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    public static String showLine() {
        return LINE;
    }

    /**
     * Shows an error when loading a file.
     */
    public static void showLoadingError() {
        showLine();
        System.out.println("Error: Cannot load file!");
        showLine();
    }
}
