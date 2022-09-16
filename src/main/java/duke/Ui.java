package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public String showError(String... message) {
        return String.join(" \n\n", message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the full command entered by the user.
     * @return the next full line from the user input
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showLoadingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but I can't load your tasks :-(");
    }

    public void showSavingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but I can't save your tasks :-(");
    }

    public void showNoTasks() {
        System.out.println("You have no tasks.\n" + "What can I do for you?");
    }

    /**
     * Prints message after adding task with current TaskList size.
     * @param task the task that was added
     * @param size the size of the TaskList after adding the task
     */
    public String showAddTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have "
                + size
                + output;
    }

    /**
     * Prints message after deleting task with current TaskList size.
     * @param task the task that was deleted
     * @param size the size of the TaskList after deleting the task
     */
    public String showDeleteTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        return "Got it. I've deleted this task:\n"
                + task
                + "\nNow you have "
                + size
                + output;
    }

    public String showList(TaskList tasks) {
        return tasks.toString();
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public void showInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        String logo = "TASKY\n";
        return "Hello! I'm " + logo
                + "What can I do for you?";
    }
}
