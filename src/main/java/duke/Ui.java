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
     * Creates a Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a welcome message for the user.
     *
     * @return A welcome message for the user.
     */
    public String showWelcome() {
        return "Hello! I'm Edric\nWhat can I do for you?";
    }

    /**
     * Returns a goodbye message for the user.
     *
     * @return A goodbye message for the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println("__________________________________________________________");
    }

    /**
     * Returns a message to indicate a given error.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a message to indicate a task has been added to the list.
     *
     * @param task The task that has been added to the list.
     * @param tasks The current list of tasks.
     * @return A message to indicate that a task has been added to the list.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:" + "\n"
                + "\t " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list";
    }

    /**
     * Returns a message to indicate a task has been deleted from the list.
     *
     * @param task The task that has been deleted from the list.
     * @param tasks The current list of tasks.
     * @return A message to indicate a task has been deleted from the list.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Got it. I've removed this task:" + "\n"
                + "\t " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list";
    }

    /**
     * Returns a message to indicate a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A message to indicate a task has been marked as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:" + "\n"
                + "\t" + task.toString();
    }

    /**
     * Returns a message to indicate a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A message to indicate a task has been marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n"
                + "\t" + task.toString();
    }

    /**
     * Returns a message containing all the Tasks in the given TaskList.
     *
     * @param tasks The List of Tasks.
     * @param message The message to be shown before the tasks.
     * @return All tasks currently in the list.
     */
    public String showTasks(TaskList tasks, String message) {
        String allTasks = message;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            allTasks += String.format("\n%d. %s", i + 1, curr.toString());
        }
        return allTasks;
    }

    /**
     * Returns the user input.
     *
     * @return A String representing the full command inputted by the user.
     */
    public String readCommand() {
        assert this.sc != null;
        return sc.nextLine();
    }

}
