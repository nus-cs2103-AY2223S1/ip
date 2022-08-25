package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;

    public TaskList(Storage storage){
        this.arr = new ArrayList<Task>();
        storage.arr = this.arr;
    }

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }
    /**
     * Adds a task to the array list.
     *
     * @param task the new task to be added
     */
    public void add(Task task) {
        this.arr.add(task);
    }

    /**
     * Deletes a task from the array list.
     *
     * @param i the position of task to be deleted
     * @return the deleted task
     */
    public Task delete(int i) throws IndexOutOfBoundsException {
        return this.arr.remove(i);
    }

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list
     */
    public int getSize() {
        return this.arr.size();
    }

    /**
     * Return a particular task from the array list.
     *
     * @param i the position of the task to be returned
     * @return a task
     */
    public Task getTask(int i) {
        return this.arr.get(i);
    }

    public ArrayList<Task> getArr() {
        return this.arr;
    }
}
