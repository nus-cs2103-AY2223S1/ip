package task;

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

    // taskId from 1-length
    public Task markTask(Integer taskId) throws IllegalArgumentException {
        try {
            Task task = tasks.get(taskId - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(String.format("Task number %d does not exist :(", taskId));
        }
    }

    public Task unmarkTask(Integer taskId) throws IllegalArgumentException {
        try {
            Task task = tasks.get(taskId - 1);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(String.format("Task number %d does not exist :(", taskId));
        }
    }

    /**
     * @return a List of all the Tasks at the current time
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
