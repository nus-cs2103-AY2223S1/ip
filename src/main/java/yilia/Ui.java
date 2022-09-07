package yilia;

import yilia.task.Task;
import yilia.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface to interact with users.
 */
public class Ui {
    /**
     * Greets users by rendering a welcome message.
     */
    public void showWelcome() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n"
                + "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n"
                + "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n"
                + "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n"
                + "      | |      | |  | |_______   | |   / /       \\ \\  \n"
                + "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        System.out.println("Hello! I'm Yilia\n" + logo + "What can I do for you?");
    }

    /**
     * Returns a string from user input.
     *
     * @return The text read from user input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Shows a division line.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Shows an error message.
     *
     * @param message The error massage.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the status of successfully unmarking a task as not done.
     */
    public void showLoadingError() {
        System.out.println("The file cannot be loaded.");
    }

    /**
     * Shows the status of successfully adding a task into the task list.
     *
     * @param tasks The task list.
     */
    public void showAddStatus(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size())
                + "\nNow you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Shows the status of successfully marking a task as done.
     *
     * @param task The task whose marking status is shown.
     */
    public void showMarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Shows the status of successfully unmarking a task as not done.
     *
     * @param task The task whose unmarking status is shown.
     */
    public void showUnmarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }
    /**
     * Shows the status of successfully deleting a task.
     *
     * @param task The task to delete.
     * @param tasks The task list.
     */
    public void showDeleteStatus(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have "
                + tasks.size() + " tasks in the list.");
    }
    /**
     * Shows the content of a task.
     *
     * @param index The index of the task.
     * @param tasks The task list.
     */
    public void showTask(int index, TaskList tasks) {
        System.out.println(index + "." + tasks.get(index));
    }
    /**
     * Shows the index out of bounds.
     *
     * @param index The index of the task.
     */
    public void showIndexOutOfBounds(int index) {
        System.out.println("Index " + index + " out of bounds\nPlease input another index");
    }
    /**
     * Farewells to users.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
