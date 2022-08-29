package duke;

/**
 * Used to interact with the user
 */
import java.util.Scanner;
public class Ui {
    /**
     * Prints welcome message for user
     */
    public static void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints error message for user
     */
    public static void showLoadingError() {
        System.out.println("Sorry, an error has occured");
    }

    /**
     * Obtains command from user
     * @return command from user
     */
    public static String getCommand() {
        Scanner nextCommand = new Scanner(System.in);
        return nextCommand.nextLine();
    }

    /**
     * Prints exit message for user
     */
    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
