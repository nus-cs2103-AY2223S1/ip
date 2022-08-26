package Duke.task;

import java.util.ArrayList;

/** Represents a list of tasks
 * @author Nam Minh Quan
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }
    /** List out all tasks in the list
     */
    public ArrayList<Task> listTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }

    /** Adds a new Duke.Duke.task into the list
     * @param task the Duke.Duke.task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark a Duke.Duke.task in the list as done
     * @param index index of the Duke.Duke.task to be marked as done
     */
    public Task mark(int index) {
        Task temp = tasks.get(index-1);
        temp.mark();
        return temp;
    }
    /**
     * Mark a Duke.Duke.task in the list as not done
     * @param index index of the Duke.Duke.task to be unmarked
     */
    public Task unmark(int index) {
        Task temp = tasks.get(index-1);
        temp.unmark();
        return temp;
    }

    /**
     * Delete a Duke.Duke.task from the Duke.Duke.task list
     * @param index index of the Duke.Duke.task to be deleted
     */
    public Task delete(int index) {
        Task temp = tasks.get(index-1);
        tasks.remove(index-1);
        return temp;
    }
}
