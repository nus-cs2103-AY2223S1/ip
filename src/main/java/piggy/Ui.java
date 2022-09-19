package piggy;

import piggy.task.Task;

import java.util.List;

/**
 * Manages the user-interaction UI.
 */
class Ui {

    /**
     * Creates a new UI object.
     */
    Ui() {
    }

    /**
     * Get the welcome message.
     *
     * @return the welcome message.
     */
    String getWelcome() {
        return "Hello! I'm Piggy\n" + "What can I oink for you?\n" + "Use the following format for datetime: " +
                "yyyy-MM-dd HH:mm\n";
    }

    /**
     * Gets the bye message to be shown upon exiting.
     *
     * @return the bye message.
     */
    String getBye() {
        return "Bye. Hope to oink you again soon!";
    }

    /**
     * Gets a message showing the list of tasks on the output.
     *
     * @param tasks The list of tasks to show.
     * @return the list of tasks message.
     */
    String getTaskList(List<Task> tasks) {
        return "Here are the tasks in your list:\n" + getTasks(tasks);
    }

    private String getTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Get a message showing a task has been added along with the remaining tasks.
     *
     * @param task The added task to show.
     * @param noRemainingTasks The number of remaining tasks.
     * @return the task added
     */
    String getTaskAdded(Task task, int noRemainingTasks) {
        return "Got it. I've added this task:\n" + "  " + task + "\n" + "Now you have " + noRemainingTasks + " " +
                "tasks in the list.\n";
    }

    /**
     * Gets a message that a task has been removed along with the remaining tasks.
     *
     * @param task The removed task to show.
     * @param noRemainingTasks The number of remaining tasks.
     * @return the task removed message
     */
    String getTaskRemoved(Task task, int noRemainingTasks) {
        return "Noted. I've removed this task: \n" + "  " + task + "Now you have " + noRemainingTasks + " tasks in" +
                " the list.\n";
    }

    /**
     * Gets a message that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The message that the task has been marked as done.
     */
    String getMarkAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Gets a message that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return The message that the task has been marked as not done.
     */
    String getMarkAsNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Get the list of tasks found.
     *
     * @param tasks The list of tasks.
     * @return The list of tasks found.
     */
    String getTasksFound(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("No matching tasks found in your list.\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            sb.append(getTasks(tasks));
        }
        return sb.toString();
    }

    /**
     * Gets a DukeException message.
     *
     * @param err The exception to show.
     * @return the message
     */
    String getDukeException(DukeException err) {
        return err.toString();
    }
}
