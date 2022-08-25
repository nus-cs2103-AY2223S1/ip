package poolsheen;

import java.util.ArrayList;

import poolsheen.task.Task;

/**
 * Represents a collection of all the tasks that Poolsheen remembers.
 */
public class TaskList {
    ArrayList<Task> arl;

    /**
     * Public constructor to initialise an already filled TaskList.
     *
     * @param arl The ArrayList of tasks to fill up the TaskList.
     */
    public TaskList(ArrayList<Task> arl) {
        this.arl = arl;
    }

    /**
     * Public constructor to initialise an empty TaskList.
     */
    public TaskList() {
        this.arl = new ArrayList<>(100);
    }

    public boolean isEmpty() {
        return arl.isEmpty();
    }

    public int size() {
        return arl.size();
    }

    public Task get(int index) {
        return arl.get(index);
    }

    public void add(Task t) {
        arl.add(t);
    }

    /**
     * Marks a task as done assuming the user input is correct.
     *
     * @param pos The index position of the task in the list.
     */
    public void mark(int pos) {
        Task selectedTask = this.arl.get(pos - 1);
        selectedTask.markAsDone();
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     *
     * @param pos The index position of the task in the list.
     */
    public void unmark(int pos) {
        Task selectedTask = this.arl.get(pos - 1);
        selectedTask.markAsNotDone();
    }

    /**
     * Removes a task from the list of tasks that Poolsheen remembers.
     *
     * @param pos The index+1 position of the task that is to be deleted.
     */
    public void deleteTask(int pos) {
        Task t = this.arl.get(pos - 1);
        this.arl.remove(pos-1);
    }
}
