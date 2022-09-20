package duke;

/**
 * Deals with interactions with the user
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Ui {

    public Ui() {
    }

    public String printTasks(TaskList tasks) {
        return "Here you go fellow cat! Tasks in your list:\n" + tasks;
    }

    public String printMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String printUnmarked(Task task) {
        return "Alright! I've marked this task as not done yet:\n" + task;
    }

    public String printAddedTag(Task task) {
        return "Nice! I've tagged the task as per your request:\n" + task + "\nKeep tagging fellow cat!";
    }

    /**
     * Shows user that the most recent tag on said task has been removed, if any
     * @param task name of task to remove tag from
     * @param removedTag tag removed from said task (if any)
     * @return description of undone tag
     */
    public String printUndoneTag(Task task, String removedTag) {
        return (removedTag == null
                ? "Hey fellow cat! There are no tags on the task to begin with! Please be careful! :("
                : "OK, I've undone the most recent tag #" + removedTag + " for your task:\n" + task);
    }

    /**
     * Informs user of the added task description and size of current TaskList
     * @param task description of added task
     * @param size size of current TaskList
     * @return task description and size of current TaskList
     */
    public String printAddedTask(Task task, int size) {
        return "Got it! I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "Stay on track fellow cat!";
    }

    /**
     * Informs user of the removed task description and size of current TaskList
     * @param task description of removed task
     * @param size size of current TaskList
     * @return task description and size of current TaskList
     */
    public String printDeletedTask(Task task, int size) {
        return "Alright! I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    // Show this message when the index of task to delete is out of bounds
    public String printNoSuchTask() {
        return "Hey fellow cat! There is no such task! Please be careful! :(";
    }

    /**
     * Just in case someone keys in the wrong filename...
     */
    public void showLoadingError() {
        System.out.println("Cannot be loaded!");
    }
}
