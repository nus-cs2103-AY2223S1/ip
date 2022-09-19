package task;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks.
 *
 * @author Marcus Low
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new task list.
     *
     * @param tasks List of tasks to be stored in the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return An integer equal to the number of tasks in the task list.
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Add a task to the task list.
     *
     * @param task Task to be added to the task list.
     */
    public static void add(Task task) {
        tasks.add(task);
    }

    /**
     * Return a task from the task list.
     *
     * @param index Index of the task in the task list.
     * @return A task in the task list at the index provided.
     */
    public static Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Remove a task from the task list.
     *
     * @param index Index of the task to be removed in the task list.
     * @return The task that is removed from the task list.
     */
    public Task delete(int index) {
        Task removed = tasks.get(index);
        tasks.remove(index);
        return removed;
    }

    /**
     * Mark a task in the task list as done.
     *
     * @param index Index of the task in the task list.
     * @return The task in the task list.
     */
    public Task mark(int index) {
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * Mark a task in the task list as not done.
     *
     * @param index Index of the task in the task list.
     * @return The task in the task list.
     */
    public Task unmark(int index) {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i <= tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                taskList += i + 1 + ". " + tasks.get(i);
            } else {
                taskList += i + 1 + ". " + tasks.get(i) + "\n";
            }
        }
        return taskList;
    }

    /**
     * List down all the tasks in the task list.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    public String find(String query) {
        String result = "";
        int index = 1;
        for (int i = 0; i < size(); i++) {
            Task curr = tasks.get(i);
            if ((curr.getDescription().toLowerCase()).contains(query)) {
                result += index++ + ". " + curr + "\n";
            }
        }
        return result;
    }
}
