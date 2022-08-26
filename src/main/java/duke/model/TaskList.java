package duke.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A list to store the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * An empty constructor for a TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * A constructor for a TaskList that uses a list of tasks.
     *
     * @param tasks the list of tasks to initialise a TaskList
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a Task from the TaskList based on the input task number.
     *
     * @param taskNumber an integer representing the task number
     * @return a Task with respect to the task number
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task the Task to be added into the TaskList
     */
    public void add(Task task) {
        tasks.add(task);
        Task.incrementNumOfTasks();
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param taskNumber an integer representing the task number
     */
    public void delete(int taskNumber) {
        tasks.remove(taskNumber - 1);
        Task.decrementNumOfTasks();
    }

    /**
     * Marks a Task as done.
     *
     * @param taskNumber an integer representing the task number
     */
    public void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
    }

    /**
     * Marks a Task as not done.
     *
     * @param taskNumber an integer representing the task number
     */
    public void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
    }

    /**
     * Returns a formatted string of a TaskList to be stored in the storage.
     *
     * @return a formatted string of a TaskList for storage
     */
    public String toStorage() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += tasks.get(i).toStorage();
        }
        return res;
    }

    /**
     * Returns a string representation of a TaskList.
     *
     * @return a string representing a TaskList
     */
    @Override
    public String toString() {
        String str = "";
        if (tasks.size() == 0) {
            str = "\tYou do not have any tasks!";
        } else {
            str = "\tHere are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                str += "\n\t" + (i + 1) + ". " + tasks.get(i);
            }
        }
        return str;
    }
}
