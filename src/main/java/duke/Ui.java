package duke;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;


/**
 * A component of the chatBot Duke that returns his response as a string.
 */
public class Ui {


    /**
     * Returns the outro message of the chatBot Duke.
     *
     * @return The outro message.
     */
    public String getOutroMessage() {
        return "Farewell, till we meet again";
    }


    /**
     * Returns the message that a task has been mark as completed.
     *
     * @param task The task that is marked as completed.
     * @return The string containing the intended message.
     */
    public String getMarkDoneMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n  " + task;
    }

    /**
     * Returns the message that a task has been mark as uncompleted.
     *
     * @param task The task that is marked as uncompleted.
     * @return The string containing the intended message.
     */
    public String getMarkUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n  " + task;

    }

    /**
     * Returns the message that a task has been deleted, along with the resultant list size.
     *
     * @param list The taskList that the task has been removed from.
     * @param task The task that is deleted.
     * @return The string containing the intended message.
     */
    public String getDeletedTaskMessage(TaskList list, Task task) {
        return "Noted. I've removed this task:" + "\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.";
    }

    /**
     * Returns the message that a task has been added, along with the resultant list size.
     *
     * @param list The taskList that the task has been added to.
     * @param task The task that is added.
     * @return The string containing the intended message.
     */
    public String getAddedTaskMessage(TaskList list, Task task) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.";

    }

    /**
     * Returns the message containing all the tasks in the given taskList.
     *
     * @param list The taskList containing the tasks.
     * @return The string containing the intended message.
     */
    public String getActiveTasksMessage(TaskList list) {
        StringBuilder strBuilder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.getListSize(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.retrieveTask(i));
        }
        return strBuilder.toString();
    }

    /**
     * Returns the message containing all the due tasks in the given arraylist.
     *
     * @param list The arraylist containing the queried tasks.
     * @return The string containing the intended message.
     */
    public String getDueTasksMessage(ArrayList<Task> list) {
        StringBuilder strBuilder = new StringBuilder("Here are the tasks due at this date:");
        for (int i = 0; i < list.size(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.get(i));
        }
        return strBuilder.toString();
    }


    /**
     * Returns the message containing all the tasks found in the query.
     *
     * @param list The given list of found tasks.
     * @return The string containing the intended message.
     */
    public String getFoundTasksMessage(ArrayList<Task> list) {
        StringBuilder strBuilder = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.get(i));
        }
        return strBuilder.toString();
    }
}

