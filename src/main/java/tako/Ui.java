package tako;

import java.util.Scanner;

import tako.task.Task;

/**
 * Deals with user interaction.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Informs the user that their task is added.
     *
     * @param task Task to add.
     * @param taskCount Total number of tasks in the task list.
     */
    public void showAdd(Task task, int taskCount) {
        System.out.println("added: " + task);
        System.out.println("Total tasks: " + taskCount);
    }

    /**
     * Informs the user that their task is deleted.
     *
     * @param task Task to delete.
     * @param taskCount Total number of tasks in the task list.
     */
    public void showDelete(Task task, int taskCount) {
        System.out.println("deleted: " + task);
        System.out.println("Total tasks: " + taskCount);
    }

    /**
     * Informs the user that an error has occurred.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println("An error has occurred.");
        System.out.println(message);
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("_______________");
    }

    /**
     * Shows all the tasks in the task list.
     *
     * @param tasks Task list to show.
     */
    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, task);
        }
    }

    /**
     * Informs the user that a loading error has occurred.
     */
    public void showLoadingError() {
        System.out.println("Tasks failed to load.");
        System.out.println("A new task list will be used instead.");
    }

    /**
     * Informs the user that their task is marked.
     *
     * @param task Task to mark.
     */
    public void showMark(Task task) {
        System.out.println("marked: " + task);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Tako.");
        System.out.println("What do you want?");
    }

    /**
     * Reads the user command.
     *
     * @return String representation of command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Informs the user that no further commands will be read.
     */
    public void exit() {
        System.out.println("Bye, until next time...");
        sc.close();
    }
}
