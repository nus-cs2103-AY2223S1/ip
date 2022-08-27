package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the Ui object responsible for printing messages to the user interface.
 *
 * @author njxue
 * @version v0.1
 */
public class Ui {
    /**
     * Greets the user when the application is launched.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }

    /**
     * Prints the prompt, which signals the user to key in an input.
     */
    public void showPrompt() {
        System.out.print("\n>>> ");
    }

    /**
     * Prints the error message when the previously saved tasks could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("FAILED! Could not find storage file containing your tasks");
        System.out.println("Add a task to generate one!!!");
    }

    /**
     * Prints the success message when all previously saves tasks were loaded.
     */
    public void showLoadingSuccess() {
        System.out.println("Yay! Your tasks were loaded successfully!");
    }

    /**
     * Prints an error message to the user interface.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the message that signifies the termination of the application.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! Hope to see you soon!");
    }

    /**
     * Prints the Task object, along with the number of tasks, when it is successfully added.
     *
     * @param task Newly added Task object.
     * @param tasks TaskList to add the new Task into.
     */
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        showTask(task);
        showNumTasks(tasks);
    }

    /**
     * Prints the Task object, along with the number of tasks, when it is successfully removed.
     *
     * @param task Removed Task object.
     * @param tasks TaskList to remove the new Task from.
     */
    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Okie, I've deleted this task: ");
        showTask(task);
        showNumTasks(tasks);
    }

    /**
     * Prints the Task object, along with the number of tasks, when it is successfully marked.
     *
     * @param task Marked Task object.
     * @param tasks TaskList containing the target Task to mark.
     */
    public void showMarkTask(Task task, TaskList tasks) {
        System.out.println("Sure! I've marked this task as done: ");
        showTask(task);
        showNumTasks(tasks);
    }

    /**
     * Prints the Task object, along with the number of tasks, when it is successfully unmarked.
     *
     * @param task Unmarked Task object.
     * @param tasks TaskList containing the target Task to unmark.
     */
    public void showUnmarkTask(Task task, TaskList tasks) {
        System.out.println("Sure! I've unmarked this task as done: ");
        showTask(task);
        showNumTasks(tasks);
    }

    /**
     * Prints the number of Task objects currently in a TaskList.
     * @param tasks Target TaskList
     */
    private void showNumTasks(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " tasks in the list");
    }

    /**
     * Prints a Task object, indented by 3 spaces.
     */
    private void showTask(Task task) {
        System.out.println("   " + task);
    }

    /**
     * Prints loading message when loading tasks from disk.
     */
    public void showIsLoading() {
        System.out.println("Loading tasks from disk.......");
    }
}
