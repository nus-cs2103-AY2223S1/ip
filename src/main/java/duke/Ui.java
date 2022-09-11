package duke;

import java.util.Comparator;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;


/**
 * A component of the chatBot Duke that returns his response as a string.
 */
public class Ui {
    public static final String INTRO_MESSAGE = "Dread it, run from it, Gigachad arrives all the same";
    public static final String EXIT_MESSAGE = "Farewell, till we meet again";

    /**
     * Returns the outro message of the chatBot Duke.
     *
     * @return The outro message.
     */
    public String getOutroMessage() {
        return EXIT_MESSAGE;
    }


    /**
     * Returns the message that a task has been mark as completed.
     *
     * @param task The task that is marked as completed.
     * @return The string containing the intended message.
     */
    public String getMarkDoneMessage(Task task) {
        return "Acknowledged. This task is done:" + "\n  " + task;
    }

    /**
     * Returns the message that a task has been mark as uncompleted.
     *
     * @param task The task that is marked as uncompleted.
     * @return The string containing the intended message.
     */
    public String getMarkUndoneMessage(Task task) {
        return "Acknowledged. This task is undone:" + "\n  " + task;
    }

    /**
     * Returns the message that a task has changed its priority.
     *
     * @param task The task that has its priority changed.
     * @return The string containing the intended message.
     */
    public String getPrioritySetMessage(Task task) {
        return "Acknowledged. Priority has changed for this task:" + "\n  " + task;
    }

    /**
     * Returns the message that a task has been deleted, along with the resultant list size.
     *
     * @param list The taskList that the task has been removed from.
     * @param task The task that is deleted.
     * @return The string containing the intended message.
     */
    public String getDeletedTaskMessage(TaskList list, Task task) {
        return "Acknowledged. This task is gone, reduced to atoms:" + "\n  " + task
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
        return "Acknowledged. Task added:\n  " + task
                + "\nNow you have " + list.getListSize() + " tasks in the list.";

    }

    /**
     * Returns the message containing all the tasks in the given taskList.
     *
     * @param list The taskList containing the tasks.
     * @return The string containing the intended message.
     */
    public String getActiveTasksMessage(TaskList list) {
        StringBuilder strBuilder = new StringBuilder("Here are all the tasks:");
        list.getList().sort(Comparator.comparing(Task::getPriority));
        for (int i = 0; i < list.getListSize(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.retrieveTask(i));
        }
        return strBuilder.toString();
    }

    /**
     * Returns the message containing all the due tasks in the given arraylist.
     *
     * @param list The list containing the queried tasks.
     * @return The string containing the intended message.
     */
    public String getDueTasksMessage(List<Task> list) {
        StringBuilder strBuilder = new StringBuilder("I found these due tasks:");
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
    public String getFoundTasksMessage(List<Task> list) {
        StringBuilder strBuilder = new StringBuilder("I found these tasks:");
        for (int i = 0; i < list.size(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.get(i));
        }
        return strBuilder.toString();
    }
}

