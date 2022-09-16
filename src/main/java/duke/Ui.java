package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * A Ui object that deals with interaction with the user.
 */
public class Ui {
    /**
     * Creates a Ui object.
     */
    public Ui() {
    }
    
    /**
     * Prompts the user to enter a command and displays all available commands.
     * 
     * @return The user prompt and the list of commands available
     */
    public static String promptUserInput() {
        return "Please enter your command: \n"
                + "The available commands are\n"
                + "1. bye\n"
                + "2. list\n"
                + "3. todo\n"
                + "4. deadline\n"
                + "5. event\n"
                + "6. mark\n"
                + "7. unmark\n"
                + "8. delete\n"
                + "9. find\n";
    }

    /**
     * Displays a welcome message to the user.
     */
    protected static String printWelcomeMessage() {
        return "Hello! I'm SoCCat\nWhat can I do for you?";
    }

    /**
     * Displays a farewell message to the user.
     */
    protected String printGoodbyeMessage() {
        return "Goodbye! SoCCat wishes to see you again soon!";
    }

    /**
     * Displays all the current tasks in the task list to the user.
     *
     * @param tasks all the current tasks in the task list
     */
    protected String printAllTasks(ArrayList<Task> tasks) {
        String allTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            allTasks = allTasks.concat(i + 1 + "." + tasks.get(i) + "\n");
        }
        return "Here are all the tasks in your list: \n" + allTasks;
    }
    
    protected String printMatchingTasks(ArrayList<Task> tasks) {
        String allTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            allTasks = allTasks.concat(i + 1 + "." + tasks.get(i) + "\n");
        }
        return "Here are the matching tasks in your list: \n" + allTasks;
    }

    /**
     * Returns a string that states how many tasks are in the task list currently.
     *
     * @param size the total number of tasks in the task list
     * @return a string that indicates how many task(s) are in the list
     */
    protected String printNumberOfTasks(int size) {
        return "Now you have " + size + (size < 2 ? " task" : " tasks") + " in your list.";
    }

    /**
     * Displays the task as marked.
     *
     * @param task the task that is marked
     */
    protected String printTaskMarked(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Displays the task as unmarked.
     *
     * @param task the task that is unmarked
     */
    protected String printTaskUnmarked(Task task) {
        return "Ok! I've marked this task as not done yet: \n" + task;
    }

    /**
     * Displays the task that was added into the task list.
     *
     * @param task the task that was added
     * @param size the total number of tasks in the task list
     */
    protected String printTaskAdded(Task task, int size) {
        return "Got it. I've added this task: \n" + task + "\n" + printNumberOfTasks(size);
    }

    /**
     * Displays the task that was deleted from the task list.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks in the task list
     */
    protected String printTaskDeleted(Task task, int size) {
        return "Noted. I've removed this task: \n" + task + "\n" + printNumberOfTasks(size);
    }

    /**
     * Displays the task that was deleted from the task list.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks in the task list
     */
    protected String printTaskUndone(Task task, int size) {
        return "I have used my magical powers and undid this task: \n" + task + "\n" + printNumberOfTasks(size);
    }
}
