package duke.managers;

import java.util.List;
import java.util.function.Predicate;

import duke.exceptions.DukeException;
import duke.models.task.Task;
import duke.storage.TaskStorage;

/**
 * Encapsulates the logic to manage the list of {@link Task tasks} and provides augmenting operations to support CRUD
 * operations on the tasks.
 *
 * @author Emily Ong Hui Qi
 */
public class TaskManager {
    private static final String MESSAGE_NO_TASKS_AVAILABLE = "There are currently no tasks available. Add one now!";
    private static final String MESSAGE_TASK_LIST_STATUS = "Now you have %s task(s) in the list.";
    private static final String MESSAGE_TASK_LIST_CANNOT_READ_STATUS = "Uh oh, I cannot read the tasks in the list!";

    private final TaskStorage taskStorage;

    /**
     * Stores a reference to the {@link TaskStorage} used in the application.
     *
     * @param taskStorage The {@link TaskStorage} used in the application
     */
    public TaskManager(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    /**
     * Displays the list of tasks in numerical order by implicitly invoking the string representation
     * of the tasks
     *
     * @param tasks The tasks to be displayed
     *
     * @return String representation of the tasks
     */
    public static String display(List<Task> tasks) {
        if (tasks.size() == 0) {
            return TaskManager.MESSAGE_NO_TASKS_AVAILABLE;
        }

        StringBuilder taskManagerDisplay = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            // Implicitly invoke the display of the task defined in the Task class
            taskManagerDisplay.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return (taskManagerDisplay + "\n").strip();
    }

    /**
     * Adds the received {@link Task} into the task storage.
     *
     * @param task Task received from the caller
     *
     * @return The added task
     * @throws DukeException If the task cannot be added
     */
    public Task add(Task task) throws DukeException {
        return this.taskStorage.addTask(task);
    }

    /**
     * Updates the specified {@link Task} corresponding to the given task index.
     *
     * @param taskNumber The 1-based task number, possibly corresponding to a particular task
     * @param task       the task to be updated
     *
     * @return The updated task
     * @throws DukeException If the task cannot be updated
     */
    public Task update(int taskNumber, Task task) throws DukeException {
        return this.taskStorage.updateTask(taskNumber - 1, task);
    }

    /**
     * Deletes the specified task number (1-index) from the {@link TaskStorage task storage} and returns the deleted
     * {@link Task}.
     *
     * @param taskNumber The 1-based task number, possibly corresponding to a particular task
     *
     * @return The deleted task
     * @throws DukeException If the task cannot be deleted
     */
    public Task delete(int taskNumber) throws DukeException {
        return this.taskStorage.deleteTask(taskNumber - 1);
    }

    /**
     * Returns the {@link Task} corresponding to the task number.
     *
     * @param taskNumber A 1-based task number, possibly corresponding to a particular task
     *
     * @return Task corresponding to the particular task number
     * @throws DukeException If the task cannot be read or retrieved
     */
    public Task get(int taskNumber) throws DukeException {
        return this.taskStorage.findTask(taskNumber - 1);
    }

    /**
     * Returns the number of {@link Task tasks} in the {@link TaskStorage task storage}.
     *
     * @return Number of tasks in the task storage
     * @throws DukeException If the tasks cannot be read
     */
    private int count() throws DukeException {
        return this.taskStorage.count();
    }

    /**
     * Returns the status of the task manager encapsulated in the form of the number of {@link Task tasks} in the
     * {@link TaskStorage task storage}.
     *
     * @return Status of the task manager
     */
    public String getStatus() {
        try {
            return String.format(TaskManager.MESSAGE_TASK_LIST_STATUS, this.count());
        } catch (DukeException e) {
            return String.format("%s: %s", TaskManager.MESSAGE_TASK_LIST_CANNOT_READ_STATUS, e.getMessage());
        }
    }

    /**
     * Returns the list of {@link Task tasks} in the {@link TaskStorage task storage}.
     *
     * @return List of tasks
     * @throws DukeException If the tasks cannot be read
     */
    public List<Task> list() throws DukeException {
        return this.taskStorage.readAllTasks();
    }

    /**
     * Returns the filtered list of {@link Task tasks} in the {@link TaskStorage task storage} that passes the
     * predicate.
     *
     * @param condition The predicate to test if the task should be returned
     *
     * @return List of tasks
     * @throws DukeException If the tasks cannot be read or retrieved
     */
    public List<Task> list(Predicate<? super Task> condition) throws DukeException {
        return this.taskStorage.filter(condition);
    }
}
