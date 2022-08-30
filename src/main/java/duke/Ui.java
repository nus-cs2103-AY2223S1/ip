package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Ui object that deals with interaction with the user.
 */
public class Ui {

    /**
     * Creates a Ui object.
     */
    public Ui() {
        Scanner scanner = new Scanner(System.in);
        welcome();
    }

    /**
     * Displays a welcome message to the user.
     */
    protected void welcome() {
        System.out.println("Hello! I'm SoCCat\nWhat can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    protected void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all the current tasks in the task list to the user.
     *
     * @param tasks all the current tasks in the task list
     */
    protected void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    /**
     * Returns a string that states how many tasks are in the task list currently.
     *
     * @param size the total number of tasks in the task list
     * @return a string that indicates how many task(s) are in the list
     */
    protected String numberOfTasks(int size) {
        return "Now you have " + size + (size < 2 ? " task" : " tasks") + " in your list.";
    }

    /**
     * Displays the task that was added into the task list.
     *
     * @param task the task that was added
     * @param size the total number of tasks in the task list
     */
    protected void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + task + "\n" + numberOfTasks(size));
    }

    /**
     * Displays the task that was deleted from the task list.
     * 
     * @param task the task that was deleted
     * @param size the total number of tasks in the task list
     */
    protected void printTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task: \n" + task + "\n" + numberOfTasks(size));
    }
    
}
