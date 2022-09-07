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
        return list.size();
    }

    /**
     * Return the task specified by the index.
     *
     * @param index Index of the element to return
     * @return Task at the index
     */
    public Task getTask(int index) {
        //-1 to index because of 0 indexing
        return list.get(index - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Remove a task from the task list specified by the index.
     *
     * @param index Index of the element to delete from the task list
     */
    public void removeTask(int index) {
        //-1 because of 0 indexing
        list.remove(index - 1);
    }

    public TaskList findRelatedTask(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                newList.add(task);
            }
        }
        return new TaskList(newList);
    }

}
