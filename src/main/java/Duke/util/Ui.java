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
    public String displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String reply = "Hello from\n" + logo + "\n" + ("Hello! I'm Duke\n" + "What can I do for you?");
        return reply;
    }

    /**
     * Displays goodbye message
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the error message
     *
     * @param error the error message to be printed
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Prints out the added task and the updated length of the task list
     *
     * @param task the task added
     * @param tasks the list of task being modified
     */
    public String printAddedTask(Task task, TaskList tasks) {
        return ("Got it. I've added this task: \n" + task.toString()
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
    public String printDeletedTask(Task task, TaskList tasks) {
        return (" Noted. I've removed this task: \n" + task.toString()
                + "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }

    /**
     * Prints out a message that responds to the user input
     *
     * @param response to be printed
     */
    public String printResponse(String response) {
        return response;
    }

    /**
     * Prints out all the tasks in the task list
     *
     * @param tasks the list to be printed
     */
    public String listTasks(ArrayList<Task> tasks) {
        String reply = "Here are the tasks in your list:\n";
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            reply += index + ". " + task.toString() + "\n";
        }
        return reply;
    }

    /**
     * Prints out confirmation that a task has been marked as done
     *
     * @param task task marked as done
     * @return String confirmation
     */
    public String printMarked(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.toString();
    }

    /**
     * Prints out confirmation that a task has been marked as not done
     *
     * @param task task marked as done
     * @return String confirmation
     */
    public String printUnmarked(Task task) {
        return "Nice! I've marked this task as not done yet: \n"
                + task.toString();
    }


    /**
     * Prints out the matching tasks
     *
     * @param tasks ArrayList of matching Tasks
     */
    public String printFoundTask(ArrayList<Task> tasks) {
        String reply = "Here are the matching tasks in your list:\n";
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            reply += index + ". " + task.toString() + "\n";
        }
        return reply;
    }
}
