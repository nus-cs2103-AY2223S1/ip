package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.utilities.DukeException;

/**
 * The TaskList class to represent the tasks.
 */
public class TaskList {
    /** Use an ArrayList Collection for handling the tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList object with an existing Arraylist of Tasks.
     *
     * @param tasks The input ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets for the tasks in the task list.
     *
     * @return Returns this.tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the number of tasks in current task list.
     *
     * @return An integer representing the number of tasks in the current list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the current task list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the current task list.
     *
     * @param index The index of the task to remove from our task list.
     * @return Returns the task object that was deleted.
     * @throws DukeException Handles duke related exceptions.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index == -1) {
            throw new DukeException("You must specify which task to delete!");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Changes the status of a task, used for marking a task as done or not done.
     *
     * @param taskId The index of the task whose status we want to change in taskList.
     * @param isDone The updated status to update the status to.
     * @return Returns the task whose status we updated.
     * @throws DukeException Handles duke related exceptions.
     */
    public Task changeTaskStatus(int taskId, boolean isDone) throws DukeException {
        if (taskId == -1) {
            throw new DukeException("You must specify which task to mark or unmark!");
        }
        Task task = tasks.get(taskId - 1);
        task.setDoneStatus(isDone);
        return task;
    }

    /**
     * Finds the tasks that have descriptions that contain the target.
     *
     * @param target The target word that we want from the tasks.
     * @return Returns a new ArrayList of tasks containing the matching tasks.
     */
    public ArrayList<Task> findMatchingTasks(String target) {
        if (target.equals("")) {
            return this.tasks;
        }

        ArrayList<Task> filtered = this.tasks.stream()
                .filter(task -> task.getDescription().contains(target))
                .collect(Collectors.toCollection(ArrayList::new));

        return filtered;
    }
}
