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
    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(LINE + initMessage + LINE);
    }

    /**
     * Prints the exit message when Duke is closed.
     */
    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + goodbyeMessage + LINE);
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads the command of the input given by the user.
     *
     * @return The input of the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
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
