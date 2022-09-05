package main.java.amanda.manager;

import java.util.ArrayList;

import main.java.amanda.task.Task;

/**
 * TaskList contains a list to hold the tasks.
 */
public class TaskList {

    protected ArrayList<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Get the current task list.
     * @return the current task list.
     */
    public ArrayList<Task> getList() {

        return list;
    }
}
