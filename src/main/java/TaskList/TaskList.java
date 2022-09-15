package TaskList;

import Exception.DukeException;
import Task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the short term storage for user created Tasks.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
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
     * @param index The index of the Task in the taskList.
     * @return The Task that has been marked as complete.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }
    /**
     * Marks the Task at the given index to be incomplete, and returns the modified Task.
     *
     * @param index The index of the Task in the TaskList.
     * @return The Task that has been marked as incomplete.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        task.markAsUndone();
        return task;
    }
    /**
     * Marks the Task at the given index to be deleted, and returns the deleted Task.
     *
     * @param index The index of the Task in the TaskList.
     * @return The Task that has been deleted.
     * @throws DukeException If the index provided is < 0 or greater than the size of the TaskList.
     */
    public Task deleteTask(int index) throws DukeException{
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        return taskList.remove(index);
    }

    /**
     * Returns the String representing all the Tasks in the TaskList.
     *
     * @return The String representing all the Tasks in the TaskList.
     */
    public String getStorageString() {
        StringBuilder out = new StringBuilder();
        for (Task task : taskList) {
            out.append(task.getStorageString());
            out.append("\n");
        }
        return out.toString();
    }

    public Task[] findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            boolean hasKeyword = task.hasKeyword(keyword);
            if (hasKeyword) {
                foundTasks.add(task);
            }
        }
        return foundTasks.toArray(new Task[0]);
    }
}
