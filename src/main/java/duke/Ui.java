/**
 * This class handles the display for user interaction.
 */
package duke;

import java.util.Scanner;

public class Ui {

    protected final String ADDED = "oke i added this:";
    protected final String DELETED = "oke i deleted this:";
    protected final String MARKED = "oke this is done now:";
    protected final String UNMARKED = "oke this is undone now:";

    private Scanner scanner;

    /**
     * Constructor for the Ui class.
     */
    public Ui () {
        System.out.println("----------------------");
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println("hi im chompers what can i do for u today!");
        System.out.println(System.getProperty("user.dir"));
        scanner = new Scanner(System.in);
    }

    /**
     * Prints an exit message.
     */
    public void showExit() {
        System.out.println("bye see u");
        scanner.close();
    }

    /**
     * Displays a task.
     *
     * @param message Message to be printed.
     * @param task Task to be displayed.
     */
    public void displayTask(String message, Task task) {
        System.out.println(message);
        System.out.println(task);
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param taskList Tasklist to be printed.
     */
    public void printTasks(TaskList taskList) {
        System.out.println("here! ur tasks:");
        System.out.println(taskList.toString());

    }

    /**
     * Displays the total amount of tasks
     * in the taskList.
     *
     * @param taskList Tasklist to be used.
     */
    public void showTotalTasks(TaskList taskList) {
        System.out.println("now u have " + taskList.getSize() + " task(s)!");
    }

    /**
     * Displays an error message.
     *
     * @param message Message to be displayed.
     */
    public void showError(String message) {
        System.out.println("error! " + message);
    }

    /**
     * Reads the user input.
     *
     * @return String containing the user input.
     */
    public String readCommand() {
        String str = "";
        scanner = new Scanner(System.in);
        str = scanner.nextLine();
        return str;
    }
}
