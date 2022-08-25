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
     * @return Size of tasklist
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * @param num Index of task
     * @return Task at index num
     */
    public Task getTask(int num) {
        return tasks.get(num);
    }

    /**
     * Add a new task into the TaskList
     * @param newTask new task added
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Remove a task at index num in the TaskList
     * @param num Index of task
     */
    public void remove(int num) {
        tasks.remove(num);
    }
}
