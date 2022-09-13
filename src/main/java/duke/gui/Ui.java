package duke.gui;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;


/**
 * An Ui class to deal with the interactions with the user.
 */
public class Ui {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Returns a success message after a task is added.
     *
     * @param task The task to be added.
     * @param taskList The list of tasks that the task is adding to.
     * @return The success message after adding a task.
     */
    public String getAddTaskCommandMessage(Task task, TaskList taskList) {
        String message = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";

        return message;
    }

    /**
     * Returns the success message after marking a task as done.
     *
     * @param task The task to be marked as done.
     * @return The success message after marking a task as done.
     */
    public String getMarkCommandMessage(Task task) {
        String message = "Nice! I've marked this as done:\n"
                + task + "\n";
        return message;
    }

    /**
     * Returns the success message after marking a task as undone.
     *
     * @param task The task to be marked as undone.
     * @return The success message after marking a task as undone.
     */
    public String getUnmarkCommandMessage(Task task) {
        String message = "Ok, I've marked this task as not done yet:\n"
                + task + "\n";
        return message;
    }

    /**
     * Returns the success message after a task is removed from the task list.
     *
     * @param task The task to be removed.
     * @param taskList The task list that the task is being removed from.
     * @return The success message after a task is removed.
     */
    public String getDeleteCommandMessage(Task task, TaskList taskList) {
        String message = "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";
        return message;
    }

    /**
     * Returns a message of the list of matching tasks.
     *
     * @param taskList The list of matching tasks.
     * @return The string representation of the list of matching tasks.
     **/
    public String getFindCommandMessage(List<Task> taskList) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            // Display task as 1-index
            str.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }

        return str.toString();
    }

    /**
     * Returns a message of all the tasks in the task list.
     *
     * @param taskList The list of tasks in Duke.
     * @return The string representation of all the tasks in the list.
     */
    public String getListCommandMessage(TaskList taskList) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            // Display task as 1-index
            str.append(i + 1).append(".").append(taskList.getTask(i)).append("\n");
        }

        return str.toString();
    }
}
