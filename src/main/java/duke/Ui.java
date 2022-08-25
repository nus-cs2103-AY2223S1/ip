package duke;

import java.util.Scanner;


/**
 * Encapsulates a class extracted from the main logic to deal with the user interface through the command line
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;



    /**
     * Prints the startup message
     */
    public void showWelcome() {
        System.out.println(GREETING);
    }

    /**
     * Prints the ending message
     */
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the User's input
     * @return The String representation of the user's input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Prints the appropriate message for adding a Task
     * @param task The task added
     * @param size The current number of tasks
     */
    public void showAddCommand(Task task,int size) {
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + size +" tasks in the list.");
    }

    /**
     * Prints the appropriate message for deleting a Task
     * @param task The task deleted
     * @param size The current number of tasks
     */
    public void showDelete(Task task,int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task.toString() +"\nNow you have " + size +" tasks in the list.");
    }

    /**
     * Prints the appropriate message for marking a Task
     * @param task The task to be marked
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    /**
     * Prints the appropriate message for unmarking a Task
     * @param task The task to be unmarked
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }



}
