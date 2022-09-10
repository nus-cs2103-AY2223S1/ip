package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList to store tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of a TaskList given by an ArrayList of task.
     *
     * @param tasks ArrayList of task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return An ArrayList with all the tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return Size of TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @param num Index of task
     * @return Task at index num
     */
    public Task getTask(int num) {
        assert(num >= 0);
        return tasks.get(num);
    }

    /**
     * Adds a new task into the TaskList.
     *
     * @param newTask new task added
     */
    public void addTask(Task newTask) {
        assert(newTask != null);
        tasks.add(newTask);
    }

    /**
     * Removes a task at index num in the TaskList.
     *
     * @param num Index of task
     */
    public void removeTask(int num) {
        assert(num >= 0);
        tasks.remove(num);
    }
}
