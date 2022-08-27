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
    /** Left padding to a string for pretty printing. */
    private String leftPadding = "   ";

    /**
     * Returns the greeting message when the application is launched.
     */
    public String greetingMessage() {
        String greeting = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke. How may I assist you?";
        return greeting;
    }

    /**
     * Returns the success message when a task has been successfully added.
     *
     * @param task Newly added Task object.
     * @param tasks TaskList to add the new Task into.
     */
    public String taskAddedMessage(Task task, TaskList tasks) {
        return String.format("Got it. I've added this task ^_^: \n%s\n%s", getIndentedTask(task), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully deleted.
     *
     * @param task Removed Task object.
     * @param tasks TaskList to remove the new Task from.
     */
    public String taskDeletedMessage(Task task, TaskList tasks) {
       return String.format("Okie, I've deleted this task >_>: \n%s\n%s", getIndentedTask(task), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully marked.
     *
     * @param task Marked Task object.
     * @param tasks TaskList containing the target Task to mark.
     */
    public String taskMarkedMessage(Task task, TaskList tasks) {
       return String.format("Sure! I've marked this task as done ^O^: \n%s\n%s", getIndentedTask(task), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully unmarked.
     *
     * @param task Unmarked Task object.
     * @param tasks TaskList containing the target Task to unmark.
     */
    public String taskUnmarkedMessage(Task task, TaskList tasks) {
        return String.format("Sure! I've marked this task as done >_>: \n%s\n%s", getIndentedTask(task), getNumTasks(tasks));
    }

    /**
     * Returns the number of tasks in a TaskList.
     *
     * @param tasks Target TaskList
     */
    private String getNumTasks(TaskList tasks) {
        return "You have " + tasks.size() + " tasks in the list";
    }

    /**
     * Returns the string representation of a Task, left padded with three spaces.
     */
    private String getIndentedTask(Task task) {
        return leftPadding + task;
    }

    /**
     * Returns the string representation of the TaskList.
     *
     * @param tasks Target TaskList
     * @return String representation of the TaskList.
     */
    public String getPrettyTaskList(TaskList tasks) {
       return tasks.toString();
    }
}
