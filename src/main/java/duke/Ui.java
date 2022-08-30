package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * Represents the User Interface of the Duke application.
 */
public class Ui {

    /**
     * Initialises the user interface of the application.
     */
    public Ui() {
    }

    /**
     * Displays the default welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Displays the default exit message.
     */
    public void showExit() {
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit);
    }

    /**
     * Displays the line separator between commands.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays all task from given TaskList.
     * @param tasks TaskList to be displayed.
     */
    public void showList(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Display list of found tasks.
     * @param tasks Task List to find in.
     * @param keyword Keyword that should match tasks found.
     */
    public void showFindResults(TaskList tasks, String keyword) {
        String message = "Here are the matching tasks in your list:\n";
        System.out.println(message + tasks.find(keyword).toString());
    }

    /**
     * Displays added message on successful addition of task.
     * @param task Task that has been added.
     * @param numTask Number of tasks in the current TaskList.
     */
    public void showAddMessage(Task task, int numTask) {
        String message = "Got it. I've added this task:\n  " + task.toString() +
                "\nNow you have " + numTask + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Displays deleted message on successful deletion of task.
     * @param task Task that has been deleted.
     * @param numTask Number of tasks in the current TaskList.
     */
    public void showDeleteMessage(Task task, int numTask) {
        String message = "Noted. I've removed this task:\n  " + task.toString() +
                "\nNow you have " + numTask + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Displays marked message on successful marking of task.
     * @param task Task that has been marked.
     */
    public void showMarkedMessage(Task task) {
        String message = "Nice! I've marked this task as done\n  " + task.toString();
        System.out.println(message);
    }

    /**
     * Displays unmarked message on successful unmarking of task.
     * @param task Task that has been unmarked.
     */
    public void showUnmarkedMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n  " + task.toString();
        System.out.println(message);
    }

    /**
     * Displays error message if file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Unable to load data");
    }

    /**
     * Displays given error message.
     * @param errorMessage Error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Get the next line of user input.
     * @return The line of user input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

}
