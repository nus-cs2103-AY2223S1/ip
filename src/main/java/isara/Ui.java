package isara;

import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.task.Task;

/**
 * Class that represents the duke.Ui, used to print
 * statements for the bot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Ui {
    /** String that represents a line. */
    protected final String line = "____________________________________________________________";

    /**
     * Prints a message when the user adds a task to the bot.
     *
     * @param task The task that is added.
     * @param amountOfTasks The total amount of tasks that has been added by the user.
     * @return Message that shows that the task has been added to the list.
     */
    public String addTask(Task task, int amountOfTasks) {
        return "Got it. I've added this task:" + "\n" + task.toString()
                + "\n" + "Now you have " + amountOfTasks + " tasks in the list.";

    }

    /**
     * Prints a message when the user adds a task to the bot.
     *
     * @param tasks The list of tasks in the list.
     * @return Message that shows that the task has been added to the list.
     */
    public String list(TaskList tasks) {
        int numberOfTasks = tasks.getNumberOfTasks();
        String output = "";
        if (numberOfTasks != 0) {
            output = "Here are the tasks in your list:" + "\n";

            int count = 1;

            for (int i = 0; i < numberOfTasks; i++) {
                Task task = tasks.getTask(i);
                String taskName = task.toString();
                output += count + "." + taskName;
                if (count != numberOfTasks) {
                    output += "\n";
                }
                count++;
            }
        } else {
            output = "You currently have no tasks.";
        }
        return output;
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task The task that is marked as undone.
     * @return Message that shows that a task has been marked.
     */
    public String mark(Task task) {
        String taskName = task.toString();
        String output = "Nice! I've marked this task as done:" + "\n";
        output += taskName;
        return output;
    }

    /**
     * Prints a message when a task is marked as undone.
     *
     * @param task The task that is marked as undone.
     * @return Message that shows that a task has been unmarked.
     */
    public String unmark(Task task) {
        String taskName = task.toString();
        String output = "OK, I've marked this task as not done yet:" + "\n";
        output += taskName;
        return output;
    }

    /**
     * Prints a message when the bot exits.
     *
     * @return Message that shows that the bot has exited.
     */
    public String exit() {
        String exitLine = "Bye. Hope to see you again soon!";
        return exitLine;
    }

    /**
     * Prints a message when the bot finds a task with a keyword.
     *
     * @param tasks The list of tasks that has been inputted by the user.
     * @return Message that lists the tasks with the keywords.
     */
    public String find(TaskList tasks) {
        String output = "";
        int numberOfTasks = tasks.getNumberOfTasks();
        if (numberOfTasks > 0) {
            output = "Here are the matching tasks in your list:" + "\n";
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                int count = i + 1;
                Task task = tasks.getTask(i);
                output += count + "." + task.toString();
                if (count != numberOfTasks) {
                    output += "\n";
                }
            }
        } else {
            output = "Oops! I'm sorry, but I can't find any tasks with that keyword :-(";
        }
        return output;
    }

    /**
     * Prints a message when a user inputs a command that does not exist.
     *
     * @return Message that is printed out when the command does not exist.
     */
    public String commandDoesNotExist() {
        return "Oops! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints a message when a user deletes their tasks.
     *
     * @param task The task that is to be deleted.
     * @param amountOfTasks The total amount of tasks that the user has left.
     * @return Message that is printed when the task is removed.
     */
    public String delete(Task task, int amountOfTasks) {
        return "Noted. I've removed this task:" + "\n" + task.toString()
                + "\n" + "Now you have "
                + amountOfTasks + " tasks in the list.";
    }

    /**
     * Prints an error message.
     *
     * @param e The exception that is to be printed.
     * @return The error message.
     */
    public String errorMessage(IsaraException e) {
        return e.toString();
    }
}
