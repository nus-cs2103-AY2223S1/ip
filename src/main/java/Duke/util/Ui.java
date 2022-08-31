package Duke.util;

import java.util.ArrayList;

import Duke.task.Task;
import Duke.task.TaskList;

/**
 * Controls what output to be printed to the console for the user to see
 */
public class Ui {
    /**
     * Displays the Duke Logo and asks user for their input
     */
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\n" + "What can I do for you?");
    }

    /**
     * Displays goodbye message
     */
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message
     *
     * @param error the error message to be printed
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints out the added task and the updated length of the task list
     *
     * @param task the task added
     * @param tasks the list of task being modified
     */
    public void printAddedTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: \n" + task.toString()
                + "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }

    /**
     * Prints out when a task has been deleted, the task deleted
     * and the updated length of the task list
     *
     * @param task
     * @param tasks
     */
    public void printDeletedTask(Task task, TaskList tasks) {
        System.out.println(" Noted. I've removed this task: \n" + task.toString()
                + "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }

    /**
     * Prints out a message that responds to the user input
     *
     * @param response to be printed
     */
    public void printResponse(String response) {
        System.out.println(response);
    }

    /**
     * Prints out all the tasks in the task list
     *
     * @param tasks the list to be printed
     */
    public void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.toString());
        }
    }

    /**
     * Prints out the matching tasks
     *
     * @param tasks ArrayList of matching Tasks
     */
    public void printFoundTask(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.toString());
        }
    }
}
