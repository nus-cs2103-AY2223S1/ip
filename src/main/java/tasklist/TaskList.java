package tasklist;

import java.util.ArrayList;
import java.util.List;

import exception.DukeException;
import task.Task;

/**
 * Represents the short term storage for user created Tasks.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
        assert taskList != null: "taskList cannot be null";
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a newly created Task to storage.
     *
     * @param newTask A newly created Task.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Marks the Task at the given index to be completed, and returns the modified Task.
     *
     * @param index The index of the Task in the taskList as printed to User.
     * @return The Task that has been marked as complete.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task markTask(int index) throws DukeException {
        index--;
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.indexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        assert task != null: "Task to be marked cannot be null";
        task.markAsDone();
        return task;
    }
    /**
     * Marks the Task at the given index to be incomplete, and returns the modified Task.
     *
     * @param index The index of the Task in the TaskList as printed to User.
     * @return The Task that has been marked as incomplete.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task unmarkTask(int index) throws DukeException {
        index--;
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.indexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        assert task != null: "Task to be unmarked cannot be null";
        task.markAsUndone();
        return task;
    }
    /**
     * Marks the Task at the given index to be deleted, and returns the deleted Task.
     *
     * @param index The index of the Task in the TaskList as printed to User.
     * @return The Task that has been deleted.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task deleteTask(int index) throws DukeException {
        index--;
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.indexOutOfBoundsException(index);
        }
        Task task = taskList.remove(index);
        assert task != null: "Task that is removed cannot be null";
        return task;
    }

    /**
     * Returns the String representing all the Tasks in the TaskList.
     *
     * @return The String representing all the Tasks in the TaskList.
     */
    public String getStorageString() {
        return taskList.stream()
                .map(Task::getStorageString)
                        .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
    }

    /**
     * Returns a list of Tasks that contain the given keyword in their description.
     *
     * @param keyword Given keyword by user to find Tasks.
     * @return A list of Tasks that are related to the keyword.
     */
    public Task[] findTasks(String keyword) {
        return taskList.stream()
                .filter(x -> x.hasKeyword(keyword))
                        .toArray(Task[]::new);
    }
}
