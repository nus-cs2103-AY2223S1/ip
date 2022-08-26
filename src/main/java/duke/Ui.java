package duke;

import task.Task;

import java.util.Scanner;

/**
 * Deals with the interaction of the user and messages to be displayed
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui object, with a scanner object used to read user inputs
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input and returns the string value
     *
     * @return the specified user input as a String
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints out a long line
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the messages after adding a task, based on the task's fields
     *
     * @param task              The specified task.
     * @param numberOfTasksLeft The number of tasks left.
     */
    public void showAddTask(Task task, int numberOfTasksLeft) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + numberOfTasksLeft + " task(s) in the list.");
    }

    /**
     * Prints the messages after removing a task, based on the task's fields
     *
     * @param task              The specified task.
     * @param numberOfTasksLeft The number of tasks left.
     */
    public void showRemoveTask(Task task, int numberOfTasksLeft) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + numberOfTasksLeft + " task(s) in the list.");
    }

    /**
     * Prints the messages after marking a task as done, based on the task's fields
     *
     * @param task The specified task.
     */
    public void showMarkTask(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    /**
     * Prints the messages after marking a task as not done, based on the task's fields
     *
     * @param task The specified task.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
    }

    /**
     * Prints the error message involved with retrieving tasks from a storage file.
     */
    public void showLoadingError() {
        System.out.println("     There was some error initialising the chatbot, no tasks are loaded.");
    }

    /**
     * Prints the welcome message whenever the chatbot starts running
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        this.showLine();
    }

    /**
     * Prints the specified tasks in a 1-indexed list format.
     *
     * @param tasks The specified tasks.
     */
    public void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Prints the current tasks of the chatbot. Used by the list command.
     *
     * @param tasks The specified tasks.
     */
    public void showCurrentTasks(TaskList tasks) {
        System.out.println("     Here are the tasks in your list:");
        this.showTasks(tasks);
    }

    /**
     * Prints the matching tasks based on user's input. Used by the find command.
     *
     * @param tasks The specified tasks.
     */
    public void showMatchingTasks(TaskList tasks) {
        System.out.println("     Here are the matching tasks in your list:");
        this.showTasks(tasks);
    }

    /**
     * Prints the goodbye message when the chatbot shuts down
     */
    public void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message to the user
     *
     * @param errorMessage The specified error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}