package duke.storage;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Default constructor which generates an empty list for storage.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a taskList using previous cache.
     * @param list The list of tasks retrieved from cache.
     */
    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * Adds one task.
     * @param task The task being added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns a task corresponding to the given index.
     * @param index The index of task.
     * @return A task corresponding to the given index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns a ArrayList of duke.Tasks.Task for iteration.
     * @return A ArrayList of duke.Tasks.Task
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns the size of ArrayList.
     * @return The size.
     */
    public int countTask() {
        return this.taskList.size();
    }

    /**
     * Removes the task being passed in.
     * @param task The task user wants to remove.
     */
    public void remove(Task task) {
        this.taskList.remove(task);
    }
}
