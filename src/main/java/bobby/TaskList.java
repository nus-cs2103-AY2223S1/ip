package bobby;
import java.util.ArrayList;

import bobby.exceptions.DukeException;
import bobby.task.Task;


public class TaskList {
    protected ArrayList<Task> list;

    /**
     * A constructor to construct the taskList
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes the task at the specified index
     * @param index the index of the task to delete
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return task;
    }

    @Override
    public String toString() {
        String result = "";
        int length = this.list.size();
        for (int i = 0; i < length; i++) {
            Task curr = this.list.get(i);
            result += String.format("%d. %s \n", i + 1, curr);
        }
        return result;
    }

    /**
     * gets the length of the list
     * @return the length of this list
     */
    public Integer length() {
        return this.list.size();
    }

    /**
     * Unmarks the task at a specific index
     * @param index the index of the task to unmark
     * @return the task that is unmarked
     */
    public Task unmarkTask(Integer index) throws DukeException {
        Task task = this.list.get(index);
        if (task.getStatusIcon().equals(" ")) {
            throw new DukeException("Task has not yet been marked!");
        }
        task.toggleStatus();
        return task;
    }

    /**
     * Marks the task at a specific index
     * @param index the index of the task to mark
     * @return the task that is marked
     */
    public Task markTask(Integer index) throws DukeException {
        Task task = this.list.get(index);
        if (task.getStatusIcon().equals("X")) {
            throw new DukeException("Task has already been completed");
        }
        task.toggleStatus();
        return task;
    }

    public void refreshTask(ArrayList<Task> newData) {
        this.list = newData;
    }

    /**
     * Filters the current tasklist based on a query
     * @param input search criteria
     * @return A TaskList of tasklist that contains the input
     */

    public TaskList filterTask(String input) {
        String comparator = "(.*)" + input.replace(" ", "(.*)") + "(.*)";
        ArrayList<Task> copyOfTasks = new ArrayList<>(this.list);
        copyOfTasks.removeIf(task -> !task.toString().matches(comparator));
        return new TaskList(copyOfTasks);
    }
}
