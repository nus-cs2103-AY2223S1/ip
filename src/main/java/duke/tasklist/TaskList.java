package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks that the user has.
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Creates instance of a task list.
     *
     * @param list ArrayList of Task type
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Return size of the task list.
     *
     * @return Number of tasks in task list
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Return the task specified by the index.
     *
     * @param index Index of the element to return
     * @return Task at the index
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Remove a task from the task list specified by the index.
     *
     * @param index Index of the element to delete from the task list
     */
    public void removeTask(int index) {
        this.list.remove(index);
    }

    public TaskList findRelatedTask(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                newList.add(task);
            }
        }
        return new TaskList(newList);
    }

}
