package duke;

import duke.exception.DukeException;
import duke.task.Task;

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

    /**
     * Prints the error given when no index is given when deleting tasks.
     */
    public static void printDeleteErrorMessage() {
        System.out.println("Please add an index to delete a task!\n");
    }

    /**
     * Prints the exception message when a DukeException is thrown.
     *
     * @param e DukeException thrown.
     */
    public static void printDukeExceptionMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the message when a Task is added to the list.
     *
     * @param t task added.
     */
    public static void printAddTaskMessage(Task t) {
        System.out.println("Added: " + t.toString() + "\n");
    }
}
