package duke;

import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to list.
     *
     * @param task task to be added.
     * @return Task added
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes tasks from list.
     *
     * @param taskNum task to be deleted.
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Marks task as done.
     *
     * @param index index of task to be marked.
     * @return Task marked.
     */
    public Task mark(int index) {
        tasks.get(index).isDone = true;
        return tasks.get(index);
    }

    /**
     * Unmarks task.
     *
     * @param index index of task to be unmarked.
     * @return Task unmarked.
     */
    public Task unmark(int index) {
        tasks.get(index).isDone = false;
        return tasks.get(index);
    }

    /**
     * Gets task list.
     *
     * @return List of task.
     */
    public ArrayList<Task> getListOfTasks() {
        return tasks;
    }

    /**
     * Gets a specific task.
     *
     * @param index index of task to get.
     * @return Task to get.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets size of task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
