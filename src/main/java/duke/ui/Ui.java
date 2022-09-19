package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out a message to user.
     *
     * @param s String of message.
     */
    public void showToUser(String s) {
        System.out.println(s);
    }

    /**
     * Displays message to user, showing task has been removed.
     *
     * @param t    Task that has been removed.
     * @param size Size of taskList.
     */
    public String showRemovingTaskMessage(Task t, int size) {
        return "\nNoted. I've removed this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list\n";
    }

    /**
     * Displays message to user, showing task has been added.
     *
     * @param t    Task that has been added.
     * @param size Size of taskList.
     */
    public String showAddingTaskMessage(Task t, int size) {
        return " Got it. I've added this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list";
    }

    public void showLoadingError(DukeException e) {
        showToUser(e.getMessage());
    }

    /**
     * Displays message to user, showing task has been marked.
     *
     * @param t Task that has been marked.
     */
    public String showMarkTaskMessage(Task t) {
        return " Nice! I've marked this task as done:\n"
                + "  [X] "
                + t.getDescription();
    }

    /**
     * Displays message to user, showing task has been unmarked.
     *
     * @param t Task that has been unmarked.
     */
    public String showUnmarkTaskMessage(Task t) {
        return " OK, I've marked this task as not done yet:\n"
                + "  [ ] "
                + t.getDescription();
    }

    /**
     * Displays message to user, showing the list of current tasks.
     *
     * @param s String of tasks.
     */
    public String showListMessage(String s) {
        return " Here are the tasks in your list:" + s;
    }

    /**
     * Displays message to user, showing exit message.
     */
    public String showExitMessage() {
        return " Bye ! hope to see you soon.";
    }

    /**
     * Displays message to user, showing the located tasks in list.
     *
     * @param s String of located tasks.
     */
    public String showFoundTask(String s) {
        return " Here are the matching tasks in your list:" + s;
    }

    public String showNumberOfTasks(int numOfTasks, String taskType) {
        return " There are " + numOfTasks + " " + taskType + " tasks";
    }

}
