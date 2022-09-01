package duke;

import java.util.ArrayList;
import java.util.List;
import duke.task.Task;

/**
 * Encapsulates the concept of a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a TaskList with no tasks stored.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList that already has some tasks stored.
     * 
     * @param persistedTasks The tasks that were stored previously.
     */
    public TaskList(List<Task> persistedTasks) {
        tasks = persistedTasks;
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The new task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public String find(String keyword) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.descriptionContains(keyword)) {
                result += (i + 1) + "." + task;
                if (i != tasks.size() - 1) {
                    result += "\n";
                }
            }
        }
        return result;
    }

    /**
     * Deletes a task from the list.
     * 
     * @param taskId The id of the task to be deleted.
     */
    public String delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return task.toString();
    }

    /**
     * Marks a task as done.
     * 
     * @param taskId The id of the task to be marked as done.
     */
    public String markAsDone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Marks a task as undone.
     * 
     * @param taskId The id of the task to be marked as undone.
     */
    public String markAsUndone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsUndone();
        return task.toString();
    }

    /**
     * Returns the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the String representation of the TaskList.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks currently.";
        }

        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
