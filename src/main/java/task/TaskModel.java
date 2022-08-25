package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.DukeException;
import exceptions.StorageException;
import storage.Storage;


/**
 * Manages state for Tasks and exposes methods for CRUD. Injected into Commands that require usage.
 */
public class TaskModel {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Initialise TaskModel
     * @throws StorageException - if there was an issue initialising storage related dependencies
     */
    public TaskModel() throws StorageException {
        this.tasks = new ArrayList<>();
        try {
            this.storage = new Storage();
            tasks = storage.load()
                    .stream()
                    .map(TaskDeserializer::deserializeTaskString)
                    .collect(Collectors.toList());
        } catch (StorageException e) {
            throw e;
        }
    }

    /**
     * Creates and adds a new Task, then returns the newly added Task.
     * @param description
     * @return The task created
     */
    public TaskResponse addTodo(String description) throws DukeException {
        Task newTask = new Todo(description, false);
        tasks.add(newTask);
        return new TaskResponse(newTask, tasks.size());
    }

    /**
     * Adds a new Deadline task
     * @param description Description for deadline
     * @param by When to finish deadline by
     * @return TaskResponse object with newly added deadline task
     */
    public TaskResponse addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by, false);
        tasks.add(deadline);
        return new TaskResponse(deadline, tasks.size());
    }

    /**
     * Adds a new Event task
     * @param description Description for event
     * @param at When the event is at
     * @return TaskResponse with newly added event task
     */
    public TaskResponse addEvent(String description, String at) {
        Task event = new Event(description, at, false);
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

    /**
     * Mark the specified task and return the marked task, or throw error
     * @param taskId Task id representing number in task list
     * @return The task that was marked
     * @throws DukeException - if taskId was invalid
     */
    // taskId from 1-length
    public Task markTask(Integer taskId) throws DukeException {
        Task task = getTask(taskId);
        task.markAsDone();
        return task;
    }

    /**
     * Unmark the specified task and return the unmarked task, or throw error
     * @param taskId Task id representing number in task list
     * @return The task that was unmarked
     * @throws DukeException - if taskId was invalid
     */
    public Task unmarkTask(Integer taskId) throws DukeException {
        Task task = getTask(taskId);
        task.unmark();
        return task;
    }

    /**
     * @param taskId Task id representing number in task list
     * @return TaskResponse with Task that was deleted
     * @throws DukeException - if taskId was invalid
     */
    public TaskResponse deleteTask(Integer taskId) throws DukeException {
        Task task = getTask(taskId);
        tasks.remove(taskId - 1);
        return new TaskResponse(task, tasks.size());
    }

    /**
     * @return a List of all the Tasks at the current time
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Save current tasks to persistent storage
     */
    public void save() throws DukeException {
        List<String> serialised = tasks.stream()
                .map(t -> t.serialize())
                .collect(Collectors.toList());
        storage.store(serialised);
    }
}
