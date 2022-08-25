package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class deals with interactions with the user.
 *
 * @author Edric Yeo
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructor for a Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Method that prints a welcome message for the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Edric\nWhat can I do for you?");
    }

    /**
     * Method that prints a goodbye message for the user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method that prints a divider line.
     */
    public void showLine() {
        System.out.println("__________________________________________________________");
    }

    /**
     * Method that prints a message to indicate a loading error.
     */
    public void showLoadingError() {
        System.out.println("Error Loading Storage File!");
    }

    /**
     * Method that prints a message to indicate a given error.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Method that prints a message to indicate a task has been added to the list.
     *
     * @param task The task that has been added to the list.
     * @param tasks The current list of tasks.
     */
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Method that prints a message to indicate a task has been deleted from the list.
     *
     * @param task The task that has been deleted from the list.
     * @param tasks The current list of tasks.
     */
    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("\t " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Method that prints a message to indicate a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Method that prints a message to indicate a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Method that prints all the Tasks in the TaskList.
     *
     * @param tasks The List of Tasks.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.format("%d. %s\n", i + 1, curr.toString());
        }
    }

    /**
     * Method that returns a String given a text command inputted by the user.
     *
     * @return A String representing the full command inputted by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

}
