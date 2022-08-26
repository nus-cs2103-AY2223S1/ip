package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.List;

/**
 * This class contains the task list.
 */
public class TaskList {
    /** List of task stored in Duke */
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
     * Returns current list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns current size of list.
     *
     * @return Size of list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns task in TaskList specified by index.
     *
     * @param index Index of task to return.
     * @return Task of specified index.
     * @throws DukeException If index was invalid, > Size or < 0.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid task number.");
        }
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from TaskList.
     *
     * @param index Index of task to delete.
     * @throws DukeException If index was invalid, > Size or < 0.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid task number.");
        }
    }
}
