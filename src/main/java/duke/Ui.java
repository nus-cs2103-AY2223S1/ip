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
    public String showWelcome() {
        return "Hello! I'm Edric\nWhat can I do for you?";
    }

    /**
     * Method that prints a goodbye message for the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
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
    public String showLoadingError() {
        return "Error Loading Storage File!";
    }

    /**
     * Method that prints a message to indicate a given error.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Method that prints a message to indicate a task has been added to the list.
     *
     * @param task The task that has been added to the list.
     * @param tasks The current list of tasks.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:" + "\n"
                + "\t " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list";
    }

    /**
     * Method that prints a message to indicate a task has been deleted from the list.
     *
     * @param task The task that has been deleted from the list.
     * @param tasks The current list of tasks.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Got it. I've removed this task:" + "\n"
                + "\t " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list";
    }

    /**
     * Method that prints a message to indicate a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:" + "\n"
                + "\t" + task.toString();
    }

    /**
     * Method that prints a message to indicate a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n"
                + "\t" + task.toString();
    }

    /**
     * Method that prints all the Tasks in the TaskList.
     *
     * @param tasks The List of Tasks.
     */
    public String showTasks(TaskList tasks) {
        StringBuilder allTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            allTasks.append(String.format("%d. %s\n", i + 1, curr.toString()));
        }
        return allTasks.toString();
    }

    /**
     * Method that returns a String given a text command inputted by the user.
     *
     * @return A String representing the full command inputted by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Method that prints out the given matching tasks.
     *
     * @param tasks The TaskList containing the matching tasks.
     */
    public String showMatchingTasks(TaskList tasks) {
        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in you list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            matchingTasks.append(String.format("%d. %s\n", i + 1, curr.toString()));
        }
        return matchingTasks.toString();
    }

}
