package duke.utils;

import java.util.List;

import duke.task.Task;

/**
 * duke.Duke utility function to handle updating of the task list while duke.Duke is running.
 */
public class TaskList {

    private List<Task> tasks;


    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the task list.
     *
     * @param task A duke.Duke Task
     * @see Task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param num The number of the task.
     */
    public void delete(int num) {
        this.tasks.remove(num);
    }

    /**
     * Gets a task from the task list.
     *
     * @param num The number of the task.
     * @return A duke.Duke Task
     * @see Task
     */
    public Task getTask(int num) {
        return this.tasks.get(num);
    }

    /**
     * Prints the tasks in the task list.
     */
    public String read() {
        String out = "";
        for (int i = 0; i < size(); i++) {
            out = out + i + 1 + ". " + getTask(i) + "\n";
        }
        return out;
    }

    /**
     * Returns size of current task list.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns current task list.
     *
     * @return Task list.
     */
    public List<Task> list() {
        return this.tasks;
    }
}
