package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/** A class to represent the face of the bot, dealing with user inputs and displaying messages to the user */
public class Ui {

    private Scanner s;
    private static final String DOUBLE_INDENT = "                ";

    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return "Hello there! My name is Zelk, it's nice to meet you :) what can I do for you today?";
    }

    /**
     * Gets the next command from the user.
     *
     * @return The input from the user.
     */
    public String readCommand() {
        return this.s.nextLine();
    }

    /**
     * Tells the user that the task that was given has been added to the to-do list.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     */
    public String showAddedTask(TaskList tasks) {
        return "new task added: \n" + tasks.getRecentTask() + "\nyou now have "
                + tasks.getNoOfTasks() + " tasks in your list";
    }

    /**
     * Tells the user that the task they have completed has been marked as done in the to-do list.
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public String showMarkedTask(String task) {
        return "alright! I've marked this task as done :)\n" + task;
    }

    /**
     * Tells the user that the task they have indicated has been marked as undone in the to-do list
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public String showUnmarkedTask(String task) {
        return "okay, I'll mark this task as undone:\n" + task;
    }

    /**
     * Displays to the user all the current tasks in the to-do list.
     *
     * @param tasks The TaskList object keeping track of all the current tasks.
     */
    public String listTasks(TaskList tasks) {
        String s = "";
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            s = s + (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return "these are the tasks in your list so far!\n" + "you currently have " + tasks.getNoOfTasks()
                + " tasks in your list:\n" + s;
    }

    /**
     * Displays to the user that the task they want to remove is being deleted from the to-do list.
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public String showDeletingTask(String task) {
        return "got it, removing this task from your list...\n" + task;
    }

    /**
     * Displays the tasks in the to-do list that matches the keyword given by the user.
     *
     * @param taskNo The list of task numbers whose tasks matches the keyword.
     * @param relevantTasks The list of matching tasks.
     */
    public String showFoundTasks(ArrayList<Integer> taskNo, ArrayList<Task> relevantTasks) {
        String s = "";
        for (int i = 0; i < taskNo.size(); i++) {
            s = s + (taskNo.get(i) + 1) + ". " + relevantTasks.get(i).toString();
        }
        return "alright, I've found " + taskNo.size() + " matching tasks in your list\n + s";
    }

    /**
     * Tells the user that an error has occurred.
     *
     * @param message The error message to display to the user.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays the exit message to the user before deactivating.
     */
    public String showExit() {
        s.close();
        return "bye! hope to see you again soon! thank you for using Zelk :D";
    }
}
