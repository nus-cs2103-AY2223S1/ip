/**
 * This class deals with the interactions with the user and prints
 * the necessary messages according to the user input
 */
package Duke;

import Tasks.Task;

import java.util.Scanner;

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
     * @throws DukeException which shows an error if there is no more lines
     *         left in the user input
     */
    public String readCommand() throws DukeException {
        if (scanner.hasNextLine())
            return scanner.nextLine();
        else {
            throw new DukeException(Constants.NO_MORE_LINES);
        }
    }

    /**
     * Prints the welcome message when user starts the program
     */
    public void showWelcome() {
        System.out.println(Constants.LINE + "\nHello! I'm " + Constants.NAME +
                "\nWhat can I do for you?\n" + Constants.LINE);
    }

    /**
     * Prints the line that segments from previous and new commands
     */
    public void showLine() {
        System.out.println(Constants.LINE);
    }

    /**
     * Prints goodbye message when user ends the program
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you soon again!");
        scanner.close();
    }

    /**
     * Prints the message for adding a task
     *
     * @param t current task that is being added into the tasklist
     * @param size of tasks left in the tasklist
     */
    public void printAddTask(Task t, int size) {
        System.out.printf("Got it. I've added this task: \n %s \nNow you have %d tasks in the list.\n",
                t.toString(), size);
    }

    /**
     * Prints the message for marking a task
     *
     * @param t current task that is being marked
     */
    public void printMarkTask(Task t) {
        System.out.printf("Nice! I've marked this task as done: \n%s \n", t.toString());
    }

    /**
     * Prints the message when unmarking a task
     *
     * @param t current task that is being unmarked
     */
    public void printUnmarkTask(Task t) {
        System.out.printf("OK, I've marked this task as not done yet: \n %s \n", t.toString());
    }

    /**
     * Prints the message when deleting a task
     * @param t current task that is being deleted
     * @param size of tasks left in the tasklist
     */
    public void printDeleteTask(Task t, int size) {
        System.out.printf("Noted. I've removed this task: \n%s " +
                "\nNow you have %d tasks in the list.\n", t.toString(), size);
    }

    /**
     * Prints the error message by Duke Exception or Exception
     *
     * @param s string that gets printed
     */
    public void showError(String s) {
        System.out.println(s);
    }
}
