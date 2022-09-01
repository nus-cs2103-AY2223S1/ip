package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * UI interactions from the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Prints an error message upon failure to load data from the saved file.
     */
    public String showLoadingError() {
        return "There was an error loading your file. Starting a new list...\n";
    }

    /**
     * Prints a farewell message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a notification that a task has been added to the list
     *
     * @param t Task that was added to the list.
     * @param len New length of the list.
     */
    public String showAdd(Task t, int len) {
        return "Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + len
                + taskString(len) + "in the list.";
    }

    /**
     * Prints a notification that a task has been deleted from the list
     *
     * @param t Task that was deleted from the list.
     * @param len New length of the list.
     */
    public String showDelete(Task t, int len) {
        String notice = "Noted. I've removed this task:\n";
        String desc = t.toString() + "\n";
        String tasksLeft = "Now you have " + len + taskString(len) + "in the list.";
        return notice + desc + tasksLeft;
    }

    /**
     * Prints a notification that a task has been marked as done.
     *
     * @param t Task that was marked as done.
     */
    public String showMark(Task t) {
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Prints a notification that a task has been unmarked as done.
     *
     * @param t Task that was unmarked as done.
     */
    public String showUnmark(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t;
    }

    /**
     * Prints a notification of tasks being found.
     */
    public String showFound() {
        return "Here are the matching tasks in your list:\n";
    }

    private String taskString(int len) {
        if (len <= 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }
}
