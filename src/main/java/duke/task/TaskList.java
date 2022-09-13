package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.DukeInvalidTypeException;
import duke.exception.DukeNoMatchException;
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

        assert hasValidState() : "Construction Failed - List provided is null";
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
        boolean isOutOfBound = index >= tasks.size() || index < 0;

        if (isOutOfBound) {
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
        boolean isOutOfBound = index >= tasks.size() || index < 0;

        if (isOutOfBound) {
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
        boolean isOutOfBound = index >= tasks.size() || index < 0;

        if (isOutOfBound) {
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
    public TaskList findTasks(String keyword) throws DukeException {
        List<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.match(keyword)) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.isEmpty()) {
            throw new DukeNoMatchException();
        }

        return new TaskList(matchedTasks);
    }

    /**
     * Deletes multiple tasks of that type from tasklist.
     *
     * @param taskType Keyword to be matched.
     */
    public void batchTypeDelete(String taskType) throws DukeException {
        int endIndex = tasks.size() - 1;
        for (int i = endIndex; i >= 0; i--) {
            deleteIfTypeMatch(taskType, i);
        }
    }

    /**
     * Deletes multiple tasks with specified description from tasklist.
     *
     * @param description description to be matched.
     */
    public void batchDescDelete(String description) throws DukeException {
        int endIndex = tasks.size() - 1;
        for (int i = endIndex; i >= 0; i--) {
            deleteIfDescMatch(description, i);
        }
    }

    /**
     * Deletes Task if Task Description contains specified description.
     *
     * @param description Specified Description to be matched
     * @param taskIndex The index of the task in TaskList.
     * @throws DukeException Exception thrown when index is invalid.
     */
    private void deleteIfDescMatch(String description, int taskIndex) throws DukeException {
        Task currentTask = tasks.get(taskIndex);
        String taskDescription = currentTask.description;

        if (taskDescription.contains(description)) {
            this.delete(taskIndex);
        }

    }

    /**
     * Deletes Task if Task is of that specified Type.
     *
     * @param taskType Specified Type to be matched
     * @param taskIndex The index of the task in TaskList.
     * @throws DukeException Exception thrown when index is invalid.
     */
    private void deleteIfTypeMatch(String taskType, int taskIndex) throws DukeException {
        Task currentTask = tasks.get(taskIndex);
        String typeCompare = getTypeofTask(currentTask);
        if (typeCompare.equalsIgnoreCase(taskType)) {
            this.delete(taskIndex);
        }
    }

    private String getTypeofTask(Task task) throws DukeException {
        if (task instanceof ToDo) {
            return "todo";
        } else if (task instanceof Event) {
            return "event";
        } else if (task instanceof Deadline) {
            return "deadline";
        } else {
            throw new DukeInvalidTypeException();
        }
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

    /**
     * Implements the class invariant.
     *
     * Perform all checks on the state of the object.
     * One may assert that this method returns true at the end
     * of every public method.
     * @return true if valid State, false otherwise.
     */
    private boolean hasValidState() {
        return isValidList(this.tasks);
    }

    /**
     * Returns validity of the list of tasks.
     *
     * @param tasks The list of tasks.
     * @return true if valid list of tasks, false otherwise.
     */
    private boolean isValidList(List<Task> tasks) {
        return tasks != null;
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
