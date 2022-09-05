package main.java.amanda.ui;

import java.util.Scanner;

import main.java.amanda.manager.TaskList;
import main.java.amanda.task.Task;

/**
 * Ui handles interactions with the user.
 */
public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui() {
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = "                                           _\n"
                + "            __ _ _ __ ___   __ _ _ __   __| | __ _\n"
                + "           / _' | '_ ` _ \\ / _` | '_ \\ / _` |/ _` |\n"
                + "          | (_| | | | | | | (_| | | | | (_| | (_| |\n"
                + "           \\__,_|_| |_| |_|\\__,_|_| |_|\\__,_|\\__,_|\n";
        System.out.println(logo);
        System.out.println("    ............................................................");
        System.out.println("     Urgh! It's you\n     What do you want?");
        System.out.println("    ............................................................\n");
    }

    /**
     * Prints separation line.
     */
    public void showLine() {
        System.out.println("    ............................................................");
    }

    /**
     * Print error messages.
     * @param string the error message.
     */
    public void showError(String string) {
        System.out.println(string);
    }

    /**
     * Print AddCommand interactions.
     * @param tasks current task list.
     * @param task task to be printed.
     */
    public void showAddCommand(TaskList tasks, Task task) {
        System.out.println("     Let's see if you will actually get this done:");
        System.out.println("        " + task);
        System.out.println("     Now you have " + tasks.getList().size() + " tasks in the list.");
    }

    /**
     * Print ListCommand interactions.
     */
    public void showListCommand() {
        System.out.println("     Here are the tasks in your list, now stop disturbing me:");
    }

    /**
     * Print MarkCommand interactions.
     * @param tasks current task list.
     * @param taskNo index of task to be marked as done.
     */
    public void showMarkCommand(TaskList tasks, int taskNo) {
        System.out.println("     Wow! You actually finished the task, I didn't think you have it in you.");
        System.out.println("        " + tasks.getList().get(taskNo - 1));
    }

    /**
     * Print UnmarkCommand interactions.
     * @param tasks current task list.
     * @param taskNo index of task to be marked as not done.
     */
    public void showUnmarkCommand(TaskList tasks, int taskNo) {
        System.out.println("     I knew it! you didn't actually finish it.");
        System.out.println("        " + tasks.getList().get(taskNo - 1));
    }

    /**
     * Print the DeleteCommand interactions.
     * @param tasks current task list.
     */
    public void showDeleteCommand(TaskList tasks) {
        System.out.println("     It's fine! Out of sight, out of mind. Am i right?");
        System.out.println("     Now you have " + tasks.getList().size() + " tasks in the list.");
    }

    /**
     * Print the exit message.
     */
    public void showExitCommand() {
        System.out.println("     Finally! I'll take a nap, please don't call me again.");
    }

    /**
     * Read the next line of user input.
     * @param sc the scanner being used.
     * @return newest line of user input as a String.
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

}
