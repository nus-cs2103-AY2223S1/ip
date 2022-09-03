package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/** A class to represent the face of the bot, dealing with user inputs and displaying messages to the user */
public class Ui {

    private Scanner s;
    private static final String INDENT = "        ";
    private static final String DOUBLE_INDENT = "                ";
    private static final String DIVIDER = " ________________________________________________________________";
    private static final String NAME = "\n"
            + DOUBLE_INDENT + " _____  _____  __     __   __ \n"
            + DOUBLE_INDENT + "|__   ||  ___||  |   |  |/  / \n"
            + DOUBLE_INDENT + "  /  / |  |__ |  |   |  |  /  \n"
            + DOUBLE_INDENT + " /  /_ |  ___||  |__ |  |\\  \\ \n"
            + DOUBLE_INDENT + "|_____||_____||_____||__| \\__\\  ";


    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("\n" + DOUBLE_INDENT + INDENT + " Hello there!\n" + DOUBLE_INDENT + INDENT + "  My name is"
                + NAME);
        System.out.println("\n" + DOUBLE_INDENT + "   it's nice to meet you :)" + "\n"
                + DOUBLE_INDENT + "what can I do for you today?" + "\n" + DIVIDER);
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
    public void showAddedTask(TaskList tasks) {
        System.out.println(INDENT + "new task added: " + tasks.getRecentTask()
                + "\n" + INDENT + "you now have " + tasks.getNoOfTasks() + " tasks in your list\n");
    }

    /**
     * Tells the user that the task they have added has been saved to the file.
     */
    public void showWrittenTask() {
        System.out.println(INDENT + "Task is saved in memory :)\n" + DIVIDER);
    }

    /**
     * Tells the user that the task they have completed has been marked as done in the to-do list.
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public void showMarkedTask(String task) {
        System.out.println(INDENT + "alright! I've marked this task as done :)\n"
                + DOUBLE_INDENT + task + "\n" + DIVIDER);
    }

    /**
     * Tells the user that the task they have indicated has been marked as undone in the to-do list
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public void showUnmarkedTask(String task) {
        System.out.println(INDENT + "Okay, I'll mark this task as undone:\n"
                + DOUBLE_INDENT + task + "\n" + DIVIDER);
    }

    /**
     * Displays to the user all the current tasks in the to-do list.
     *
     * @param tasks The TaskList object keeping track of all the current tasks.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(INDENT + "These are the tasks in your list so far!\n"
                + INDENT + "You currently have " + tasks.getNoOfTasks() + " tasks in your list:");
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            System.out.println(INDENT + (i + 1) + ". " +  tasks.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    /**
     * Displays to the user that the task they want to remove is being deleted from the to-do list.
     *
     * @param task The TaskList object keeping track of all the current tasks in the to-do list.
     */
    public void showDeletingTask(String task) {
        System.out.println(INDENT + "got it, removing this task from your list...\n"
                + INDENT + task + "\n" + DIVIDER);
    }

    /**
     * Tells the user that an error has occurred.
     *
     * @param message The error message to display to the user.
     */
    public void showError(String message) {
        System.out.println(INDENT + message + "\n" + DIVIDER);
    }

    /**
     * Displays the exit message to the user before deactivating.
     */
    public void showExit() {
        System.out.println(INDENT + "Bye! Hope to see you again soon! Thank You for using Zelk :D\n" + DIVIDER);
        s.close();
    }

    public void showFoundTasks(ArrayList<Integer> taskNo, ArrayList<Task> relevantTasks) {
        System.out.println(INDENT + "alright, I've found " + taskNo.size() +  " matching tasks in your list \n");
        for (int i = 0; i < taskNo.size(); i++) {
            System.out.println(INDENT + (taskNo.get(i) + 1) + ". " + relevantTasks.get(i).toString());
        }
        System.out.println(DIVIDER);
    }

}
