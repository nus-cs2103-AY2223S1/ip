package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Handles interaction with user.
 */
public class Ui {
    /**
     * Greets user.
     */
    public static String showWelcome() {
        String result = ("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        return result;
    }

    /**
     * Prints Task information when added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in list.
     *
     */
    public String printAddTask(Task task, int size) {
        return ("\tGot it. I've added this task:\n"
                + "\t" + task.toString() + "\n"
                + "\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints upon deletion of Task.
     *
     * @param task Task deleted.
     * @param size Remaining number of Tasks in list.
     */
    public String printDeleteTask(String task, int size) {
        return ("\tNoted. I've removed this task:\n"
                + "\t" + task + "\n"
                + "\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints list of Tasks currently.
     *
     * @param list The list of Tasks in String.
     */
    public String printDisplayList(String list) {
        String message = "\tHere are the tasks in your list:\n";
        message += list;
        return (message);
    }

    /**
     * Prints when task is marked.
     *
     * @param message The Task that was marked in String.
     */
    public String printMarkTask(String message) {
        return ("\tNice! I've marked this task as done:\n"
                + "\t" + message);
    }

    /**
     * Prints when task is unmarked.
     *
     * @param message The Task that was unmarked in String.
     */
    public String printUnmarkTask(String message) {
        return ("\tOK, I've marked this task as not done yet:\n"
                + "\t" + message);
    }

    /**
     * Prints Tasks that matched description given.
     *
     * @param list Tasks that matched description given.
     */
    public String printFindTask(String list) {
        String message = "\tHere are the matching tasks in your list:\n";
        message += list;
        return (message);
    }

    /**
     * Prints error message.
     *
     * @param e Exception caught.
     */
    public String printError(Exception e) {
        return ("\t" + e.getMessage());
    }

    /**
     * Prints when program terminates.
     */
    public String printExit() {
        return ("\tBye. Hope to see you again soon!");
    }
}
