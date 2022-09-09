package duke;

import java.util.ArrayList;

/**
 * A TaskList class that contains all the tasks of the user.
 *
 */
public class TaskList {
    private ArrayList<Task> inputs = new ArrayList<Task>();

    /**
     * Constructor of the TaskList.
     */
    TaskList() {
        this.inputs = new ArrayList<>();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return Boolean determining if the TaskList is empty.
     */
    public boolean isEmpty() {
        return this.inputs.isEmpty();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task this is the task parameter to be added.
     */
    public void addTask(Task task) {
        this.inputs.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param id this is the index of the TaskList to remove the task from.
     */
    public void removeTask(int id) {
        this.inputs.remove(id);
    }


    /**
     * Obtains the length of the TaskList.
     *
     * @return int representation of the TaskList.
     */
    public int getSize() {
        return this.inputs.size();
    }

    /**
     * Obtains a task from the TaskList.
     *
     * @param i the index that the Task is needed from the user.
     * @return Task that is required by user.
     */
    public Task getTask(int i) {
        return this.inputs.get(i);
    }

    /**
     * Obtains the index of a task.
     *
     * @param task
     * @return index of the task needed by the user.
     */
    public int getIndex(Task task) {
        return inputs.indexOf(task);
    }

    /**
     * Checks if the input task is a duplicate.
     *
     * @param task
     * @return boolean determining whether the task is a duplicate within tasklist.
     */
    public boolean isDuplicateTask(Task task) {
        for (int i = 0; i < inputs.size(); i++ ) {
            if (inputs.get(i).toString().equals(task.toString())) {
                return true;
            }
        } return false;
    }
}
