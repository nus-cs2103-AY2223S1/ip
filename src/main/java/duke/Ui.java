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
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Prints an error message upon failure to load data from the saved file.
     */
    public void showLoadingError() {
        System.out.println("There was an error loading your file. Starting a new list...\n");
    }

    /**
     * Prints a dotted line to separate commands and responses.
     */
    public void showLine() {
        System.out.println("-------------------------------");
    }

    /**
     * Prints the message of the error.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a farewell message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a notification that a task has been added to the list
     *
     * @param t Task that was added to the list.
     * @param len New length of the list.
     */
    public void showAdd(Task t, int len) {
        System.out.println("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + len
                + taskString(len) + "in the list.");
    }

    /**
     * Prints a notification that a task has been deleted from the list
     *
     * @param t Task that was deleted from the list.
     * @param len New length of the list.
     */
    public void showDelete(Task t, int len) {
        String notice = "Noted. I've removed this task:\n";
        String desc = t.toString() + "\n";
        String tasksLeft = "Now you have " + len + taskString(len) + "in the list.";
        System.out.println(notice + desc + tasksLeft);
    }

    /**
     * Prints a notification that a task has been marked as done.
     *
     * @param t Task that was marked as done.
     */
    public void showMark(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Prints a notification that a task has been unmarked as done.
     *
     * @param t Task that was unmarked as done.
     */
    public void showUnmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }

    /**
     * Reads the line that was input and determines the exact Command it is referring to.
     *
     * @return Command indicated by the input String.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    private String taskString(int len) {
        if (len <= 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }
    
}
