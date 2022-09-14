package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 *
 * @author Lim Ai Lin
 */
public class Ui {

    /**
     * Returns a message when Duke faces a loading error.
     */
    public void showLoadingError() {
        System.out.println("RAWR! Error loading previous tasks.");
    }

    /**
     * Returns a String when the user wishes to exit.
     */
    public String exit() {
        return "Bye bye. Hope to see you again soon!";
    }

    /**
     * Throws an exception when the user does not enter a valid description.
     *
     * @throws DukeException
     *          Thrown when description is empty.
     */
    public void emptyDescription() throws DukeException {
        throw new DukeException("RAWR! Empty description.");
    }

    /**
     * Throws an exception when the user does not enter a valid task.
     *
     * @throws DukeException
     *          Thrown when task is invalid.
     */
    public static void invalidTask() throws DukeException {
        throw new DukeException("RAWR! Invalid task.");
    }

    /**
     * Throws an exception when the user does not enter a date when needed.
     *
     * @throws DukeException
     *          Thrown when there is a missing date.
     */
    public void missingDate() throws DukeException {
        throw new DukeException("RAWR! Missing date.");
    }

    /**
     * Returns a message when the user has marked a task.
     *
     * @param myTask The task that the user has marked.
     */
    public String complete(Task myTask) {
        return "Hooray! You have completed this task:\n" + myTask;
    }

    /**
     * Returns a message when the user has unmarked a task.
     *
     * @param myTask The task that the user has unmarked.
     */
    public String incomplete(Task myTask) {
        return "Oh no! You have more things to complete:\n" + myTask;
    }

    /**
     * Prints a message when the user has added a new task to the list.
     *
     * @param taskList The tasklist the user has added to.
     * @param task The task the user has added.
     */
    public String add(TaskList taskList, Task task) {
        return "Dino added: " + task
        + "\nYou have " + taskList.size() + " task" + (taskList.size() > 1 ? "s!" : "!");
    }

    public String match(ArrayList<Task> matching) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks Dino found:\n");

        for (int i = 0; i < matching.size(); i++) {
            builder.append(i + 1).append(". ").append(matching.get(i).toString()).append("\n");
        }
        return builder.toString();
    }

    /**
     * Returns the message when the user has removed a new task from the list.
     *
     * @param taskList The tasklist the user has removed from.
     * @param task The task the user has removed.
     */
    public String remove(TaskList taskList, Task task) {
        return "Dino is removing " + task + "..."
            + "\nYou have " + taskList.size() + " task" + (taskList.size() > 1 ? "s!" : "!");
    }
}
