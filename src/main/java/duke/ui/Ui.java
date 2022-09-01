package duke.ui;

import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * UI of the application Inspired by AddressBook
 */
public class Ui {

    /**
     * Initialises Ui.
     */
    public Ui() {
    }

    /**
     * Method that returns a greeting to the user.
     *
     * @return helloMessage
     */
    public static String helloMessage() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Method to returns an exit message to the user.
     *
     * @return exitMessage
     */
    public static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generic method to print a message.
     *
     * @param str
     * @return message
     */
    public String printMessage(String str) {
        return "_______________________________________________________" + "\n\t" + str + "\n"
                + "_______________________________________________________";
    }

    /**
     * Method to wrap a message for printing after a new task is added.
     *
     * @param str
     * @param taskDescription
     * @param taskList
     * @return message
     */
    public String wrapMessage(String str, String taskDescription, TaskList taskList) {
        return String.format(
                str + "\n\t\t" + taskDescription + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }
}
