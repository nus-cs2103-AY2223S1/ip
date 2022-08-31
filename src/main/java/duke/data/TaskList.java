package duke.data;

import java.util.ArrayList;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.tasks.Task;

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
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Returns a Task at the given index on the list.
     * @param index The index of the Task in the list.
     * @return The Task at the given index on the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a Task as done or not done.
     * @param index The index of the Task to be marked as done or not done.
     * @param isDone True if the Task is to be marked as done and false if the Task is to
     *               be marked as not done.
     * @return The Task that has been marked as done or not done.
     * @throws DukeException If the task number is negative or greater than the number of tasks.
     */
    public Task changeTaskStatus(int index, boolean isDone) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.changeStatus(isDone);
            return task;
        } else {
            throw new DukeException(Messages.MESSAGE_NO_SUCH_TASK);
        }
    }

    /**
     * Adds a Task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task from the list of tasks.
     * @param index The index of the Task to be deleted.
     * @return The Task that has been deleted.
     * @throws DukeException If the task number is negative or greater than the number of tasks.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            tasks.remove(task);
            return task;
        } else {
            throw new DukeException(Messages.MESSAGE_NO_SUCH_TASK);
        }
    }

    /**
     * Finds matching tasks whose description contains any of the given keyword(s).
     * @param keywords The keyword(s) to check each Task's description for.
     * @return A list of tasks whose description contains any of the given keyword(s).
     */
    public ArrayList<Task> getMatchingTasks(String ... keywords) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            for (String keyword : keywords) {
                if (!keyword.isEmpty() && task.hasKeyword(keyword)) {
                    matchingTasks.add(task);
                    break;
                }
            }
        }
        return matchingTasks;
    }
}
