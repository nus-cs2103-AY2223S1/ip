import java.util.ArrayList;

/**
 * This class handles the interactions with the user.
 */
public class UI {

    /**
     * Displays the Greeting message.
     */
    public static void Greet() {
        String logo = " _______               \n"
                + "|  _____|  _   _____   \n"
                + "|  |____  | | |  __ |  \n"
                + "|   ____| | | |  ___|  \n"
                + "|  |____  | | | |      \n"
                + "|_______| |_| |_|";
        System.out.println("Greetings from Elp\n" + logo);
        System.out.println("What can I help you with?\n");
    }

    /**
     * Prints Goodbye Message.
     */
    public static void Goodbye() {
        System.out.println("Have a nice day! :)");
    }

    public static void printDeleteErrorMessage() {
        System.out.println("Please add an index to delete a task!\n");
    }

    public static void printDukeExceptionMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    public static void printAddTaskMessage(Task t) {
        System.out.println("Added: " + t.toString() + "\n");
    }
}
