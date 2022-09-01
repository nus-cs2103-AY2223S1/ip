package duke.data;

import java.io.Serializable;
import java.util.LinkedList;

import duke.task.Task;

/**
 * Stores and retrieves the Task objects.
 */
public class TaskList implements Serializable {

    /** Linked list to store the user's tasks */
    private LinkedList<Task> tasks;


    /**
     * Creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }


    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int indexNumber) {
        return this.tasks.get(indexNumber);
    }


    /**
     * Sets a Task object at the specified index.
     * 
     * @param indexNumber Index number to set the Task object.
     * @param t Task object to set.
     */
    public void setTask(int indexNumber, Task t) {
        this.tasks.set(indexNumber, t);
    }


    /**
     * Adds a Task object to the end of the list of tasks.
     * 
     * @param t Task object to add.
     */
    public void addTask(Task t) {
        this.tasks.addLast(t);
    }


    /**
     * Removes and returns the Task object at the specified index.
     * 
     * @param indexNumber Index number of Task object to remove.
     * @return Task object that was removed.
     */
    public Task removeTask(int indexNumber) {
        Task t = this.tasks.remove(indexNumber);
        return t;
    }


    /**
     * Returns the string representation of the TaskList object.
     * 
     * @return String representation of the TaskList object.
     */
    @Override
    public String toString() {
        return String.format("%s", this.tasks.toString());
    }
    
}
