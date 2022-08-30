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
    private final BufferedReader in;
    private final PrintStream out;

    /**
     * Initialises Ui to allow input and output.
     */
    public Ui() {
        this(new InputStreamReader(System.in), System.out);
    }

    /**
     * Initialises Ui to allow input and output.
     *
     * @param in
     * @param out
     */
    public Ui(InputStreamReader in, PrintStream out) {
        this.in = new BufferedReader(in);
        this.out = out;
    }

    /**
     * Method to read inputs from user line by line.
     *
     * @return input
     */
    public String readCommand() {
        String input = "";
        try {
            input = in.readLine();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
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
     * Method to returns a exit message to the user.
     *
     * @return exitMessage
     */
    public static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generic method to print a message.
     *
     * @param strArray
     */
    public void printMessage(String[] strArray) {
        this.out.println("_______________________________________________________");
        this.out.println("\tHere are the tasks in your list:");
        for (String str : strArray) {
            this.out.println("\t" + str);
        }
        this.out.println("_______________________________________________________");
    }

    /**
     * Generic method to print a message.
     *
     * @param strArray
     * @param message
     * @return printString
     */
    public void printMessage(String[] strArray, String message) {
        this.out.println("_______________________________________________________");
        this.out.println(String.format("\t %s", message));
        for (String str : strArray) {
            this.out.println("\t" + str);
        }
        this.out.println("_______________________________________________________");
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
