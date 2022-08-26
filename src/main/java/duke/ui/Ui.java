package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/** Deals with interactions with the user */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Display welcome message to user.
     */
    public void showWelcome() {
        showLine();
        showToUser(" Hello! I'm Duke\n"
                + " What can I do for you?");
        showLine();
    }

    /**
     * Reads the next input by user.
     *
     * @return String of the input.
     */
    public String readCommand() {
        return sc.nextLine();
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
     * Show divider line.
     */
    public void showLine() {
        showToUser("____________________________________________________________");
    }

    /**
     * Display message to user, showing task has been removed.
     *
     * @param t Task that has been removed.
     * @param size Size of taskList.
     */
    public void showRemovingTaskMessage(Task t, int size) {
        showToUser("\nNoted. I've removed this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list\n");
    }

    /**
     * Display message to user, showing task has been added.
     *
     * @param t Task that has been added.
     * @param size Size of taskList.
     */
    public void showAddingTaskMessage(Task t, int size) {
        showToUser(" Got it. I've added this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list");
    }

    public void showLoadingError(DukeException e) {
        showToUser(e.getMessage());
    }

    /**
     * Display message to user, showing task has been marked.
     *
     * @param t Task that has been marked.
     */
    public void showMarkTaskMessage(Task t) {
        showToUser(" Nice! I've marked this task as done:\n"
                + "  [X] "
                + t.getDescription());
    }

    /**
     * Display message to user, showing task has been unmarked.
     *
     * @param t Task that has been unmarked.
     */
    public void showUnmarkTaskMessage(Task t) {
        showToUser(" OK, I've marked this task as not done yet:\n"
                + "  [ ] "
                + t.getDescription());
    }

    /**
     * Display message to user, showing the list of current tasks.
     *
     * @param s String of tasks.
     */
    public void showListMessage(String s) {
        showToUser(" Here are the tasks in your list:" + s);
    }

    /**
     * Display message to user, showing exit message.
     */
    public void showExitMessage() {
        showToUser(" Bye ! hope to see you soon.");
    }

    /**
     * Display message to user, showing the located tasks in list.
     *
     * @param s String of located tasks.
     */
    public void showFoundTask(String s) {
        showToUser(" Here are the matching tasks in your list:" + s);
    }
}
