package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.List;

/**
 * This class manages the list of tasks that has been registered by the user.
 * This class provides methods to access and modify the tasks stored by the user.
 */
public class TaskList {
    private List<Task> storedTasks;

    public TaskList(List<Task> storedTask) {
        this.storedTasks = storedTask;
    }

    public List<Task> getStoredTasks() {
        return this.storedTasks;
    }

    public int getSize() {
        return this.storedTasks.size();
    }

    public Task getTask(int index) throws DukeException {
        try {
            return storedTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }

    public void setTaskDoneStatus(int index, boolean isDone) throws DukeException {
        try {
            storedTasks.get(index).setIsDone(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }

    public void addTask(Task task) {
        storedTasks.add(task);
    }

    public void deleteTask(int i) throws DukeException {
        try {
            storedTasks.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }
}
