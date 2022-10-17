package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * The TaskList class contains the List of Tasks and has operations to add
 * or delete tasks in the List.
 *
 * @author Edric Yeo
 */
public class TaskList {

    /** List containing all the tasks currently in the TaskList */
    private List<Task> tasks;

    /**
     * Creates a TaskList instance containing the given list of tasks.
     *
     * @param tasks The List of Tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for a TaskList instance that has no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of Tasks in the List.
     *
     * @return The number of Tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a Task at a given index of the List.
     *
     * @param idx The index of the task.
     * @return The Task at the given index.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Adds a Task to the List.
     *
     * @param task The Task to add to the List.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The deleted Task.
     * @throws DukeException If the index is out of range.
     */
    public Task deleteTask(int idx) throws DukeException {
        if (idx >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task deleted = tasks.get(idx);
        assert deleted != null;
        tasks.remove(idx);
        return deleted;
    }

    /**
     * Marks a Task (as done) at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The Task marked as done.
     * @throws DukeException If the index is out of range.
     */
    public Task markTask(int idx) throws DukeException {
        if (idx >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(idx);
        assert task != null;
        task.mark();
        return task;
    }

    /**
     * Marks a Task (as not done) at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The Task marked as not done.
     * @throws DukeException If the index is out of range.
     */
    public Task unmarkTask(int idx) throws DukeException {
        if (idx >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(idx);
        assert task != null;
        task.unmark();
        return task;
    }

    /**
     * Saves and updates the data file.
     *
     * @param filePath The filePath to the data file to write to.
     * @throws IOException if filePath is invalid.
     */
    public void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            fw.write(curr.toDataEntry());
        }
        fw.close();
    }

    /**
     * Returns matching tasks, given a keyword.
     *
     * @param keyword The String representing the matching keyword.
     * @return The TaskList containing the matching words.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            String desc = curr.toString();
            if (desc.contains(keyword)) {
                matchingTasks.addTask(curr);
            }
        }
        return matchingTasks;
    }
}
