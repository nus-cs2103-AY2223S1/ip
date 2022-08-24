package duke.data;

import duke.data.exception.DukeException;
import duke.common.Message;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList from a given list of tasks.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for a TaskList when there is an error loading the list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int numTasks() {
        return this.tasks.size();
    }

    /**
     * Returns a Task at the given index on the list.
     * @param index The index of the Task in the list.
     * @return The Task at the given index on the list.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a Task as done or not done.
     * @param index The index of the Task to be marked as done or not done.
     * @param isDone True if the Task is to be marked as done and false if the Task is to
     *               be marked as not done.
     * @return The Task that has been marked as done or not done.
     */
    public Task changeTaskStatus(int index, boolean isDone) throws DukeException {
        if (index >=0 && index < this.tasks.size()) {
            Task task = this.tasks.get(index);
            task.changeStatus(isDone);
            return task;
        } else {
            throw new DukeException(Message.NO_SUCH_TASK);
        }
    }

    /**
     * Adds a Task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a Task from the list of tasks.
     * @param index The index of the Task to be deleted.
     * @return The Task that has been deleted.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index >=0 && index < this.tasks.size()) {
            Task task = this.tasks.get(index);
            this.tasks.remove(task);
            return task;
        } else {
            throw new DukeException(Message.NO_SUCH_TASK);
        }
    }
}
