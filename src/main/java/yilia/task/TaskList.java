package yilia.task;

import java.util.ArrayList;

/**
 * Represents a task list that contains a lot of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Returns the size of the task list.
     *
     * @return The size.
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Returns the task of a specific index int the list.
     *
     * @return the task.
     */
    public Task get(int index) {
        return tasks.get(index - 1);
    }
    /**
     * Adds a task to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Removes a task from the task list.
     *
     * @return The task to be removed.
     */
    public Task remove(int index) {
        return tasks.remove(index - 1);
    }
    /**
     * Filters the task list to generate a new one.
     *
     * @return The task list generated.
     */
    public TaskList filter(String content) {
        TaskList newList = new TaskList();
        tasks.stream()
             .filter(task -> task.contains(content))
             .forEach(task -> newList.add(task));
        return newList;
    }
    public boolean anyMatch(Task newTask) {
        return tasks.stream().anyMatch(task -> task.equals(newTask));
    }
}
