package sally.ui;

import sally.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class where all ui is handled.
 *
 * @author liviamil
 */

public class Ui {
    protected String BORDER ="-------------------------------------------------------------------------------------";
    private Scanner sc;

    /**
     * Constructor for Ui Class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints parsed inputs with borders
     *
     * @param s messages to be displayed with borders
     */
    public void printWithBorder(String s) {
        System.out.println(BORDER);
        System.out.println(s);
        System.out.println(BORDER);
    }

    /**
     * Shows greetings at the beginning
     */
    public void showGreeting() {
        printWithBorder("Hello! I'm Sally\nWhat can I do for you?");
    }

    /**
     * Gets the user command
     *
     * @return user command as string
     */
    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Gets the list in String format of tasks
     *
     * @param tasks to retrieve the list from
     * @return String format of list
     */
    public String getList(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            int number = i + 1;
            output = output + number + ". " + tasks.getTask(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Shows the list to user
     *
     * @param tasks to retrieve the list from
     */
    public void showList(TaskList tasks) {
        if (tasks.getNumOfTasks() == 0) {
            printWithBorder("You don't have any list right now");
        } else {
            printWithBorder("Here's your current list:\n" + getList(tasks));
        }
    }

    /**
     * Shows the deleted task to user
     *
     * @param removed task to show to user
     */
    public void showDeleted(String removed) {
        printWithBorder("This task has been removed from your to-do list:\n" + removed);
    }

    /**
     * Shows the unmarked task to user
     *
     * @param unmarkedTask task to show to user
     */
    public void showUnmarked(String unmarkedTask) {
        printWithBorder("Got it, I've unmarked this task for you!\n" + unmarkedTask);
    }

    /**
     * Shows the previously unmarked task to user
     *
     * @param notMarked task to show to user
     */
    public void showPreviouslyUnmarked(String notMarked) {
        printWithBorder("You have not marked: \n  " + notMarked);
    }

    /**
     * Shows the marked task to user
     *
     * @param markedTask task to show to user
     */
    public void showMarked(String markedTask) {
        printWithBorder("Got it, I've marked this task for you!\n" + markedTask);
    }

    /**
     * Shows the previously marked task to user
     *
     * @param marked task to show to user
     */
    public void showPreviouslyMarked(String marked) {
        printWithBorder("You have previously done: \n" + marked);
    }

    /**
     * Shows the added task to user
     *
     * @param tasks to show to user
     */
    public void showAddedTask(TaskList tasks) {
        int taskNum = tasks.getNumOfTasks();
        String message = (taskNum == 1)
            ? "Now you have 1 task in your list."
            : "Now you have " + taskNum + " tasks in your list.";
        printWithBorder("Got it. I've added this task:\n" + tasks.getTask(taskNum - 1).toString() + "\nto your list! " + message);
    }

    /**
     * Shows goodbye message to user
     */
    public void showGoodbye() {
        printWithBorder("Ok, until next time!");
    }

    /**
     * Shows error message to user
     *
     * @param error message to be shown
     */
    public void showError(String error) {
        printWithBorder(error);
    }
}
