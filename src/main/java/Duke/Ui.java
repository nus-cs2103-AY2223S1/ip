package Duke;

import Tasks.Task;

import java.util.Scanner;

/**
 * This class deals with the interactions with the user and prints
 * the necessary messages according to the user input
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initialises a new UI with a scanner to get user input
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the command input by the user
     *
     * @return string containing the user input per line
     */
    public String readCommand() {
        try {
            return scanner.nextLine();
        }
        catch (Exception e) {
            return Constants.NO_MORE_LINES;
        }
    }

    /**
     * Prints the welcome message when user starts the program
     *
     * @return string that shows the welcome message
     */
    public String showWelcome() {
        return (Constants.LINE + "\nHello! I'm " + Constants.NAME +
                "\nWhat can I do for you?\n" + Constants.LINE);
    }

    /**
     * Prints the line that segments from previous and new commands
     *
     * @return string that shows the line printed
     */
    public String showLine() {
        return (Constants.LINE);
    }

    /**
     * Prints goodbye message when user ends the program
     *
     * @return string that returns the final output to be printed when ending the bot
     */
    public String printBye() {
        String str = ("Bye. Hope to see you soon again!");
        scanner.close();
        return str;
    }

    /**
     * Prints the message for adding a task
     *
     * @param t current task that is being added into the tasklist
     * @param size of tasks left in the tasklist
     * @return string that returns the final output to be printed when adding a task
     */
    public String printAddTask(Task t, int size) {
        return String.format("Got it. I've added this task: \n %s \nNow you have %d tasks in the list.\n",
                t.toString(), size);
    }

    /**
     * Prints the message for marking a task
     *
     * @param t current task that is being marked
     * @return string that returns the final output to be printed when marking a task
     */
    public String printMarkTask(Task t) {
        return String.format("Nice! I've marked this task as done: \n%s \n", t.toString());
    }

    /**
     * Prints the message when unmarking a task
     *
     * @param t current task that is being unmarked
     * @return string that returns the final output to be printed when unmarking a task
     */
    public String printUnmarkTask(Task t) {
        return String.format("OK, I've marked this task as not done yet: \n %s \n", t.toString());
    }

    /**
     * Prints the message when deleting a task
     *
     * @param t current task that is being deleted
     * @param size of tasks left in the tasklist
     * @return string that returns the final output to be printed for a deleted task
     */
    public String printDeleteTask(Task t, int size) {
        return String.format("Noted. I've removed this task: \n%s " +
                "\nNow you have %d tasks in the list.\n", t.toString(), size);
    }

    /**
     * Prints the error message by Duke Exception or Exception
     *
     * @param s string that gets printed
     * @return string that returns the error to be printed
     */
    public String showError(String s) {
        return s;
    }
}
