package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class which deals with interactions with the user.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Ui {
    /**
     * Blank spaces to prefix a string for printing.
     */
    private static final String FORMAT_TAB = "    ";
    /**
     * String that is used to separate the printed outputs.
     */
    private static final String FORMAT_LINEBREAK = "___________________________________";
    /**
     * Scanner object.
     */
    private Scanner sc;

    /**
     * A constructor to initialize Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Get the command that the user input.
     *
     * @return command the user input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints the content with blank spaces prefixed.
     *
     * @param content The string to be printed on the console.
     */
    public void printTab(String content) {
        System.out.println(FORMAT_TAB + content);
    }

    /**
     * Prints a line on the output console.
     */
    public void showLine() {
        printTab(FORMAT_LINEBREAK);
    }

    /**
     * Prints a greeting message when the program starts.
     */
    public void printGreetings() {
        showLine();
        printTab("Hello! I'm Duke");
        printTab("What can I do for you?");
        showLine();
        System.out.println();
    }

    /**
     * Prints the message when a task is added to the tasklist.
     *
     * @param t The task to be added.
     * @param tasks The tasklist that the task is added to.
     */
    public void printAddingTask(Task t, TaskList tasks) {
        showLine();
        printTab("Got it. I've added this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", tasks.size());
        printTab(temp);
        showLine();
    }

    /**
     * Prints a goodbye message when the program ends.
     */
    public void printGoodbye() {
        showLine();
        printTab("Bye. Hope to see you again soon!");
        showLine();
        System.out.println();
    }

    /**
     * Prints the error message.
     *
     * @param e The error thrown.
     */
    public void printError(Exception e) {
        showLine();
        printTab(e.getMessage());
        showLine();
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param t The task that is marked.
     */
    public void printMarked(Task t) {
        showLine();
        printTab("Nice! I've marked this task as done:");
        printTab("  " + t);
        showLine();
    }

    /**
     * Prints the message when a task is unmarked.
     *
     * @param t The task that is unmarked.
     */
    public void printUnMarked(Task t) {
        showLine();
        printTab("OK, I've marked this task as not done yet:");
        printTab("  " + t);
        showLine();
    }

    /**
     * Prints the list of tasks in tasklist.
     *
     * @param tasks The list of tasks.
     */
    public void printCurrentList(TaskList tasks) {
        showLine();
        printTab("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String temp = String.format("%d. %s", i + 1, t);
            printTab(temp);
        }
        showLine();
    }

    /**
     * Prints the string.
     *
     * @param s The string to be printed.
     */
    public void echo(String s) {
        showLine();
        printTab(s);
        showLine();
        System.out.println();
    }

    /**
     * Prints the message when a task is deleted from a tasklist.
     *
     * @param t The tasks to be deleted.
     * @param tasks The tasklist that the task is deleted from.
     */
    public void printDelete(Task t, TaskList tasks) {
        showLine();
        printTab("Noted. I've removed this task:");
        printTab("  " + t);
        String temp = String.format("Now you have %d tasks in the list.", tasks.size());
        printTab(temp);
        showLine();
    }
}
