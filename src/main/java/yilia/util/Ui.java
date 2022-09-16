package yilia.util;

import java.util.Scanner;

import yilia.task.Task;
import yilia.task.TaskList;

/**
 * Represents the user interface to interact with users.
 */
public class Ui {
    /**
     * Greets users by rendering a welcome message.
     * return A welcome message.
     */
    public String showWelcome() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n"
                + "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n"
                + "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n"
                + "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n"
                + "      | |      | |  | |_______   | |   / /       \\ \\  \n"
                + "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        String message = "Hello! I'm Yilia\n" + logo + "What can I do for you?";
        System.out.println(message);
        return message;
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
     * @return a line.
     */
    public String showLine() {
        String line = "_______";
        System.out.println(line);
        return line;
    }

    /**
     * Shows an error message.
     *
     * @param message The error massage.
     * @return The error message.
     */
    public String showError(String message) {
        message = "(｀･д･′) " + message;
        System.out.println(message);
        return message;
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
     * @return The status after adding.
     */
    public String showAddStatus(TaskList tasks) {
        String message = "ヾ（′▽｀*）ゝGot it! \nI've added this task:\n  " + tasks.get(tasks.size())
                + "\nNow you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.";
        System.out.println(message);
        return message;
    }
    /**
     * Shows the status of successfully marking a task as done.
     *
     * @param task The task whose marking status is shown.
     * @return The status after marking.
     */
    public String showMarkStatus(Task task) {
        String message = "Nice! (o≖◡≖)\nI've marked this task as done:\n  " + task;
        System.out.println(message);
        return message;
    }
    /**
     * Shows the status of successfully unmarking a task as not done.
     *
     * @param task The task whose unmarking status is shown.
     * @return The status after unmarking.
     */
    public String showUnmarkStatus(Task task) {
        String message = "Nice! (o≖◡≖)\nI've marked this task as done:\n  " + task;
        System.out.println(message);
        return message;
    }
    /**
     * Shows the status of successfully deleting a task.
     *
     * @param task The task to delete.
     * @param tasks The task list.
     * @return The status after deleting.
     */
    public String showDeleteStatus(Task task, TaskList tasks) {
        String message = "Noted. |･ω･｀) I've removed this task:\n  " + task + "\nNow you have "
                + tasks.size() + " tasks in the list.\n";
        System.out.println(message);
        return message;
    }
    /**
     * Shows the content of a task.
     *
     * @param tasks The task list.
     * @return The information of a task.
     */
    public String showTask(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append("ヾ(◍°∇°◍)ﾉﾞHere are your tasks:");
        for (int i = 1; i <= tasks.size(); i++) {
            message.append("\n");
            message.append(tasks.get(i));
        }
        System.out.println(message.toString());
        return message.toString();
    }
    /**
     * Shows the index out of bounds.
     *
     * @param index The index of the task.
     * @return An error message.
     */
    public String showIndexOutOfBounds(int index) {
        String message = "(＃｀д´)ﾉ Index " + index + " out of bound.\nPlease input another index";
        System.out.println(message);
        return message;
    }
    /**
     * Shows duplicate task.
     *
     * @param task The task that already exists in the task list.
     * @return A message to inform users of the duplicate task.
     */
    public String showDuplicate(Task task) {
        String message = "The task " + task + " already exists in the task list.( ´･ω･)ﾉ(._.`)";
        System.out.println(message);
        return message;
    }
    /**
     * Farewells to users.
     * @return A goodbye message.
     */
    public String showBye() {
        String message = "Bye. Hope to see you again soon! (｡･ω･｡)ﾉ♡";
        System.out.println(message);
        return message;
    }
}
