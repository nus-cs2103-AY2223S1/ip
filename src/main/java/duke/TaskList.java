package duke;

import java.util.ArrayList;

public class TaskList {

    /**
     * List containing all the users tasks.
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     * @param i index in the list.
     * @return Task at specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds task to list.
     * @param t Task to be added.
     */
    public void add (Task t) {
        tasks.add(t);
    }

    /**
     * Removes task at specified index from the list.
     * @param i Index to remove task.
     * @return Task removed from list.
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

}

