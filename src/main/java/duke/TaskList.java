package duke;

import java.util.ArrayList;

/**
 * Class that stores Tasks in ArrayList
 * @author Ashiqur Rahman A0230107Y
 */
public class TaskList {
    protected ArrayList<Task> allTasks;

    /**
     * Constructor for TaskList class
     * @param allTasks All Tasks
     */
    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    /**
     * Constructor for TaskList class
     */
    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    /**
     * Method to return size of taskList
     * @return size of TaskList
     */
    public int size() {
        return this.allTasks.size();
    }

    /**
     * Method to get task
     * @return task of specified index in TaskList
     */
    public Task get(int index) {
        return this.allTasks.get(index);
    }

    /**
     * Method to add task
     */
    public void add(Task t) {
        this.allTasks.add(t);
    }

    /**
     * Method to remove task
     * @return the removed Task
     */
    public Task remove(int index) {
        return this.allTasks.remove(index);
    }
}
