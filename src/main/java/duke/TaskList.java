package duke;

import java.util.ArrayList;

/**
 * TaskList representing user's list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a new blank TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with the specified list of tasks added to the TaskList.
     * @param tasks List of tasks to be added to this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the Task at index i of this TaskList.
     * @param i index of Task to be retrieved and returned.
     * @return Task at index i of this TaskList.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds the specified Task to the back of this TaskList.
     * @param t Specified task to be added to this TaskList.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the Task at the specified index from this TaskList.
     * @param i Index at which Task will be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns the number of Tasks in this TaskList.
     * @return An integer representing the number of Tasks in this TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    public boolean isTarget(int index, String toFind) {
        return this.get(index).getDescription().indexOf(toFind) != -1;
    }


    /**
     * Returns a TaskList containing Tasks that matches the String provided.
     * @param s An identifier to filter out relevant Tasks to be returned.
     * @return A TaskList with a list of tasks that matches the String provided.
     */
    public TaskList find(String s) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (isTarget(i, s)) {
                temp.add(tasks.get(i));
            }
        }
        return new TaskList(temp);
    }
}
