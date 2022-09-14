package duke.commons;

import java.util.List;
import java.util.stream.Stream;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class contains the task list.
 */
public class TaskList {
    private static final String MESSAGE_INVALID_INDEX = "OOPS!!! Invalid task number.";
    private List<Task> taskList;

    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param taskList Specified list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns task in TaskList specified by index.
     *
     * @param index Index of task to get.
     * @return Task of specified index.
     * @throws DukeException If index was invalid, that is, > Size or < 0.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from TaskList.
     *
     * @param index Index of task to delete.
     * @throws DukeException If index was invalid, that is, > Size or < 0.
     */
    public void removeTask(int index) throws DukeException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Returns current number of tasks in TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns index of task in the TaskList.
     *
     * @param task Task to check index of.
     * @return Index of task.
     */
    public int getIndexOf(Task task) {
        return taskList.indexOf(task);
    }

    /**
     * Returns true if taskList is empty, else false.
     *
     * @return If taskList is empty.
     */
    public Boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Converts TaskList into a stream of tasks.
     *
     * @return Stream of tasks.
     */
    public Stream<Task> toStream() {
        return taskList.stream();
    }
}
