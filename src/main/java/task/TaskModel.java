package task;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages state for Tasks and exposes methods for CRUD. Injected into Commands that require usage.
 */
public class TaskModel {
    List<Task> tasks;
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }

    /** Creates and adds a new Task, then returns the newly added Task.
     * @param description
     * @return The task created
     */
    public TaskResponse addTodo(String description) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        return new TaskResponse(newTask, tasks.size());
    }

    public TaskResponse addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        return new TaskResponse(deadline, tasks.size());
    }

    public TaskResponse addEvent(String description, String at) {
        Task event = new Event(description, at);
        tasks.add(event);
        return new TaskResponse(event, tasks.size());
    }

    private Task getTask(Integer taskId) throws DukeException {
        try {
            Task task = tasks.get(taskId - 1);
            return task;
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(String.format("Task number %d does not exist :(", taskId));
        }
    }

    // taskId from 1-length
    public Task markTask(Integer taskId) throws DukeException {
        Task task = getTask(taskId);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(Integer taskId) throws DukeException {
        Task task = getTask(taskId);
        task.unmark();
        return task;
    }

    /**
     * @return a List of all the Tasks at the current time
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
