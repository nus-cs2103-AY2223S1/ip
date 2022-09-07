package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.models.serializable.TaskSerializable;
import duke.models.task.Task;

/**
 * Encapsulates the logic for managing and interacting with the storage for storing {@link Task} data.
 *
 * @author Emily Ong Hui Qi
 */
public class TaskStorage extends Storage {
    private static final String TASK_FILE_NAME = "duke.txt";

    private static final String ERROR_ADDING_TASK_TO_STORAGE = "Error adding task to storage!";
    private static final String ERROR_TASK_NOT_FOUND = "Task with id %d is not found!";

    /**
     * Initializes the task storage with the file path for where tasks information are persisted.
     */
    public TaskStorage() {
        super(TaskStorage.TASK_FILE_NAME);
    }

    /**
     * Adds a {@link Task} to the task storage.
     *
     * @param addingTask Task object to be added.
     *
     * @return The added task object.
     * @throws DukeException If the task cannot be added.
     */
    public Task addTask(Task addingTask) throws DukeException {
        this.appendTask(addingTask);
        return addingTask;
    }

    /**
     * Returns the {@link Task} specified by the task index.
     *
     * @param taskIndex 0-indexed specifier for the task.
     *
     * @return The corresponding task.
     * @throws DukeException If the task cannot be found.
     */
    public Task findTask(int taskIndex) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskStorage.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        return allTasks.get(taskIndex);
    }

    /**
     * Deletes the {@link Task} corresponding to the task index from the task storage and returns the deleted task.
     *
     * @param taskIndex The task index corresponding to the task to be deleted.
     *
     * @return The deleted task.
     * @throws DukeException If the task cannot be deleted.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskStorage.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        Task deletedTask = allTasks.remove(taskIndex);
        this.writeAllTasks(allTasks);
        return deletedTask;
    }

    /**
     * Updates the {@link Task} corresponding to the task index using the updated Task object provided by the caller,
     * and returns the updated task.
     *
     * @param taskIndex    0-based indexed specifier for the task to be updated.
     * @param updatingTask The task object containing the updated task.
     *
     * @return The updated task.
     * @throws DukeException If the task cannot be updated.
     */
    public Task updateTask(int taskIndex, Task updatingTask) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskStorage.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        allTasks.set(taskIndex, updatingTask);
        this.writeAllTasks(allTasks);
        return updatingTask;
    }

    /**
     * Returns the number of {@link Task tasks} in the task storage.
     *
     * @return Number of tasks in the task storage.
     * @throws DukeException If the tasks cannot be read.
     */
    public int count() throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        return allTasks.size();
    }

    /**
     * Returns a filtered {@link Task} list based on the provided predicate.
     *
     * @param condition Predicate provided for the filter operation.
     *
     * @return Filtered task list.
     * @throws DukeException If the tasks cannot be read or retrieved.
     */
    public List<Task> filter(Predicate<? super Task> condition) throws DukeException {
        return this.readAllTasks().stream().filter(condition).collect(Collectors.toList());
    }

    /**
     * Returns the list of {@link Task tasks} in the task storage.
     *
     * @return List of tasks in the task storage.
     * @throws DukeException If the tasks cannot be read.
     */
    public List<Task> readAllTasks() throws DukeException {
        File storage = this.getStorage();
        Scanner fileReader;

        try {
            fileReader = new Scanner(storage);
        } catch (FileNotFoundException e) {
            throw new DukeException(ERROR_STORAGE_NOT_INITIALIZED);
        }

        ArrayList<Task> allTasks = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            TaskSerializable serializable = TaskSerializable.from(fileReader.nextLine());
            allTasks.add(serializable.deserialize());
        }
        return allTasks;
    }

    private void writeAllTasks(List<Task> tasks) throws DukeException {
        File storage = this.getStorage();
        try {
            FileWriter fileWriter = new FileWriter(storage);
            for (Task task : tasks) {
                fileWriter.write(task.serialize().toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(TaskStorage.ERROR_ADDING_TASK_TO_STORAGE);
        }
    }

    private void appendTask(Task task) throws DukeException {
        File storage = this.getStorage();
        try {
            // Set the 'append' flag to true to avoid overwriting existing file entries
            FileWriter fileWriter = new FileWriter(storage, true);
            fileWriter.write(task.serialize().toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(TaskStorage.ERROR_ADDING_TASK_TO_STORAGE);
        }
    }
}
