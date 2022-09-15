package sally.ui;

import sally.main.MainWindow;
import sally.task.Task;
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
    private MainWindow mainWindow;

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
     * Displays Sally's responses to the main window
     *
     * @param s message to be displayed
     */
    public void displaySally(String s) {
        mainWindow.addSallyDialog(s);
    }

    /**
     * Shows greetings at the beginning
     */
    public void showGreeting() {
        displaySally("Hello! I'm Sally\nWhat can I do for you?");
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
            displaySally("You don't have any list right now");
        } else {
            displaySally("Here's your current list:\n" + getList(tasks));
        }
    }

    /**
     * Shows the deleted task to user
     *
     * @param removed task to show to user
     */
    public void showDeleted(String removed) {
        displaySally("This task has been removed from your to-do list:\n" + removed);
    }

    /**
     * Shows the unmarked task to user
     *
     * @param unmarkedTask task to show to user
     */
    public void showUnmarked(String unmarkedTask) {
        displaySally("Got it, I've unmarked this task for you!\n" + unmarkedTask);
    }

    /**
     * Shows the previously unmarked task to user
     *
     * @param notMarked task to show to user
     */
    public void showPreviouslyUnmarked(String notMarked) {
        displaySally("You have not marked: \n  " + notMarked);
    }

    /**
     * Shows the marked task to user
     *
     * @param markedTask task to show to user
     */
    public void showMarked(String markedTask) {
        displaySally("Got it, I've marked this task for you!\n" + markedTask);
    }

    /**
     * Shows the previously marked task to user
     *
     * @param marked task to show to user
     */
    public void showPreviouslyMarked(String marked) {
        displaySally("You have previously done: \n" + marked);
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
        displaySally("Got it. I've added this task:\n" + tasks.getTask(taskNum - 1).toString() + "\nto your list! " + message);
    }

    /**
     * Shows goodbye message to user
     */
    public void showGoodbye() {
        displaySally("Bye, until next time!");
    }

    /**
     * Shows error message to user
     *
     * @param error message to be shown
     */
    public void showError(String error) {
        displaySally(error);
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        String output = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            output = output + (i + 1) + ". " + foundTasks.get(i).toString() + "\n";
        }
        displaySally("Here are the matching tasks in your list: \n" + output);
    }

    /**
     * Sets the main window
     *
     * @param mw main window to be set
     */
    public void setMainWindow(MainWindow mw) {
        this.mainWindow = mw;
    }
}
