package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeOutOfBoundException;

/**
 * A TaskList class that stores the list of Tasks.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks List to store the tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task into TaskList.
     *
     * @param task Task to be added.
     * @return Task added.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes task from TaskList.
     *
     * @param index index of task to be deleted in TaskList.
     * @return The task being removed.
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public Task delete(int index) throws DukeOutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeOutOfBoundException();
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Marks Task as complete.
     *
     * @param index Index of task to be marked as complete.
     * @return The task being marked.
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public Task mark(int index) throws DukeOutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeOutOfBoundException();
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Unmark task as complete.
     *
     * @param index index of task to be unmarked.
     * @return The task being undo-ed.
     * @throws DukeOutOfBoundException Exception thrown when index is out of size of TaskList.
     */
    public Task unmark(int index) throws DukeOutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeOutOfBoundException();
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * List tasks with matching description.
     *
     * @param keyword Keyword to be matched.
     */
    public TaskList findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.match(keyword)) {
                matchedTasks.add(task);
            }
        }

        return new TaskList(matchedTasks);
    }

    /**
     * Gets TaskList.
     *
     * @return The TaskList.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets size of TaskList.
     *
     * @return Size of taskList.
     */
    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("%d. %s\n", i + 1, tasks.get(i).toString());
            result.append(task);
        }
        return result.toString();
    }
}
