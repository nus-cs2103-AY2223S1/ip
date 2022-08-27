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
     * @return String containing display message to user.
     */
    public String showList(TaskList tl) {
        StringBuilder sb = new StringBuilder();
        List<Task> taskList = tl.getTasks();
        int index = 1;
        sb.append("Here are the tasks in your list nya:\n");
        for (Task t : taskList) {
            sb.append(index + "." + t + "\n");
            index++;
        }
        return sb.toString();
    }

    /**
     * Displays the current taskList to the user.
     *
     * @param tl the taskList to be shown to the user.
     * @param filter the filter to find tasks by.
     * @return String containing display message to user.
     */
    public String showList(TaskList tl, String filter) {
        StringBuilder sb = new StringBuilder();
        List<Task> taskList = tl.getTasks();
        int index = 1;
        sb.append("Here are the matching tasks in your list nya:\n");
        for (Task t : taskList) {
            if (t.description.contains(filter)) {
                sb.append(index + "." + t + "\n");
                index++;
            }
        }
        return sb.toString();
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
     * @return the string message to be shown to the user.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Informs the user that a particular task has been added.
     *
     * @param task the task that has been added.
     * @param size the size of the current taskList.
     * @return string containing display message to the user.
     */
    public String showAddTask(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Roger nya! Added this task:\n  " + task.toString() + "\n");
        sb.append("Now you have " + size + " task(s) in the list nya");
        return sb.toString();
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @return the string containing the display message to the user.
     */
    public String showMarkTask() {
        return "I've marked this task as done. Great job nya!";
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @return the string containing the display message to the user.
     */
    public String showUnmarkTask() {
        return "Roger nya! I've marked this task as not done.";
    }

    /**
     * Informs the user that a particular task has been deleted.
     *
     * @param task the task that has been deleted.
     * @param size the size of the taskList.
     * @return string containing display message to user.
     */
    public String showDeleteTask(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Roger nya! I've removed this task:\n  " + task.toString() + "\n");
        sb.append("Now you have " + size + " task(s) left in the list.");
        return sb.toString();
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
