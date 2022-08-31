package duke;

/**
 * Used to interact with the user
 */
import java.util.Scanner;
public class Ui {
    /**
     * Prints welcome message for user
     */
    public static String showWelcome() {
        return("Hello! I'm Duke\nWhat can I do for you?" +
                "\n" + "icons provided by Freepik and Trazobanana");
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
    public static String showGoodbye() {
        return("Bye. Hope to see you again soon!");
    }
}
