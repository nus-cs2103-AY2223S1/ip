package duke.tools;

import java.util.List;

import duke.exceptions.DukeException;
import duke.tasks.Task;

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
        return taskList;
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
     * Returns true if taskList is empty, else false.
     *
     * @return If taskList is empty.
     */
    public Boolean isEmpty() {
        return taskList.isEmpty();
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
