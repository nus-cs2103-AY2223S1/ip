package pony;

import java.util.ArrayList;
import java.util.Scanner;

import pony.task.Task;



/**
 * A UI class handling all user interaction.
 */
public class Ui {
    Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    };

    /**
     * Generate the welcome message.
     *
     * @return A welcome message.
     */
    public static String printWelcome() {
        String message = "Hello! I'm Pony!!!!\nNever forget - Friendship is Magic!!!\n"
                + "My dear friend, what can I do for you?";
        return message;
    }

    /**
     * Generate an exit message.
     *
     * @return An exit message.
     */
    public String printExit() {
        String message = "Bye, friend. Hope to see you again soon!\nNever forget - Friendship is Magic!!!";
        return message;
    }

    /**
     * Generate a message when a task is marked.
     *
     * @param task Task to be mark.
     * @return A command message.
     */
    public String printMarkedTask(Task task) {
        String message = "Well done, my friend! I've marked this task as done:\n";
        message += task.toString();
        return message;
    }

    /**
     * Generate a message when a task is unmarked.
     *
     * @param task Task to be unmark.
     * @return A command message.
     */
    public String printUnmarkedTask(Task task) {
        String message = "My friend, I've marked this task as not done yet:\n";
        message += task.toString();
        return message;
    }

    /**
     * Generate a massage when a task is deleted.
     *
     * @param task Task to be deleted.
     * @param tasks A list of tasks.
     * @return A command message.
     */
    public String printDeletedTask(Task task, TaskList tasks) {
        String message = "Alright my friend, I've removed this task:\n";
        message += task.toString() + "\n" + "Now you have " + tasks.sizeOf() + " tasks in the list.";
        return message;
    }

    /**
     * Generate a message when a task is added.
     *
     * @param task Task to be added.
     * @param tasks A list of tasks.
     * @return A command message.
     */
    public String printAddedTask(Task task, TaskList tasks) {
        String message = "Got it my friend. I've added this task:\n";
        message += task.toString() + "\n" + "Now you have " + tasks.sizeOf() + " tasks in the list.";
        return message;
    }

    /**
     * Generate the task list.
     *
     * @param tasks A list of tasks.
     * @return A command message.
     */
    public String printTaskList(TaskList tasks) {
        String message = "";
        if (tasks.sizeOf() == 0) {
            message = "There is nothing on the list!";
        } else {
            message = "Let's look at the tasks in your list:\n";
            for (int i = 0; i < tasks.sizeOf(); i++) {
                int serialNumber = i + 1;
                message += serialNumber + ". " + tasks.getTask(i).toString() + "\n";
            }
        }
        return message;
    }

    /**
     * Generate a message of a find command.
     *
     * @param tasks A list of tasks.
     * @return A list of matching tasks.
     */
    public String printFindResult(ArrayList<Task> tasks) {
        String message = "";
        if (tasks.size() == 0) {
            message = "There is no matching task!";
        } else {
            message = "Alright, Here's what pony found!\n";
            for (int i = 0; i < tasks.size(); i++) {
                int serialNumber = i + 1;
                message += serialNumber + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return message;
    }
}
