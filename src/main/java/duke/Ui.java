package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for displaying the user interface of Duke.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the current taskList to the user.
     *
     * @param tl the taskList to be shown to the user.
     */
    public void showList(TaskList tl) {
        List<Task> taskList = tl.getTasks();
        int index = 1;
        System.out.println("Here are the tasks in your list nya:");
        for (Task t : taskList) {
            System.out.println(index + "." + t);
            index++;
        }
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.print("Hi I'm catBot!\nHow can I help you nya?\n");
    }

    /**
     * Reads the first line of user input that has been entered.
     *
     * @return the first line of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the error message to the user.
     *
     * @param message the error message to be shown to the user.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Informs the user that a particular task has been added.
     *
     * @param task the task that has been added.
     * @param size the size of the current taskList.
     */
    public void showAddTask(Task task, int size) {
        System.out.println("Roger nya! Added this task:\n  " + task.toString());
        System.out.println("Now you have " + size + " task(s) in the list nya");
    }

    /**
     * Informs the user that a task has been marked as done.
     */
    public void showMarkTask() {
        System.out.println("I've marked this task as done. Great job nya!");
    }

    /**
     * Informs the user that a task has been marked as not done.
     */
    public void showUnmarkTask() {
        System.out.println("Roger nya! I've marked this task as not done.");
    }

    /**
     * Informs the user that a particular task has been deleted.
     *
     * @param task the task that has been deleted.
     * @param size the size of the taskList.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("Roger nya! I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + size + " task(s) left in the list.");
    }

    /**
     * Displays a horizontal line to the user to separate inputs.
     */
    public void showLine() {

    }

    /**
     * Displays the goodbye message to uesr before application exits.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye nya! Hope to see you again nya");
    }
}
