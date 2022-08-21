package database;

import exceptions.DukeException;
import models.Task;
import models.TaskSerializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskDatabase extends Database {
    private static final String TASK_FILE_NAME = "duke.txt";

    private static final String ERROR_ADDING_TASK_TO_DATABASE = "Error adding task to database!";
    private static final String ERROR_TASK_NOT_FOUND = "Task with id %d is not found!";

    public TaskDatabase() {
        super(TaskDatabase.TASK_FILE_NAME);
    }

    /**
     * Adds a task to the task database
     *
     * @param addingTask Task object to be added
     * @return The added task object
     * @throws DukeException If the task cannot be added
     */
    public Task addTask(Task addingTask) throws DukeException {
        this.appendTask(addingTask);
        return addingTask;
    }

    /**
     * Returns the task specified by the task index
     * @param taskIndex 0-indexed specifier for the task
     * @return The corresponding task
     * @throws DukeException If the task cannot be found
     */
    public Task findTask(int taskIndex) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskDatabase.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        return allTasks.get(taskIndex);
    }

    /**
     * Deletes the task corresponding to the task index from the task database and returns
     * the deleted task
     *
     * @param taskIndex The task index corresponding to the task to be deleted
     * @return The deleted task
     * @throws DukeException If the task cannot be deleted
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskDatabase.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        Task deletedTask = allTasks.remove(taskIndex);
        this.writeAllTasks(allTasks);
        return deletedTask;
    }

    /**
     * Updates the task corresponding to the task index using the updated Task object
     * provided by the caller, and returns the updated task
     * @param taskIndex 0-based indexed specifier for the task to be updated
     * @param updatingTask The task object containing the updated task
     * @return The updated task
     * @throws DukeException If the task cannot be updated
     */
    public Task updateTask(int taskIndex, Task updatingTask) throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new DukeException(String.format(TaskDatabase.ERROR_TASK_NOT_FOUND, taskIndex + 1));
        }
        allTasks.set(taskIndex, updatingTask);
        this.writeAllTasks(allTasks);
        return updatingTask;
    }

    /**
     * Returns the number of tasks in the task database
     * @return Number of tasks in the task database
     * @throws DukeException If the tasks cannot be read
     */
    public int count() throws DukeException {
        List<Task> allTasks = this.readAllTasks();
        return allTasks.size();
    }

    /**
     * Returns the list of tasks in the task database
     * @return List of tasks in the task database
     * @throws DukeException If the tasks cannot be read
     */
    public List<Task> readAllTasks() throws DukeException {
        File database = this.getDatabase();
        Scanner fileReader;

        try {
            fileReader = new Scanner(database);
        } catch (FileNotFoundException e) {
            throw new DukeException(Database.ERROR_DATABASE_NOT_INITIALIZED);
        }

        ArrayList<Task> allTasks = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            TaskSerializable serializable = TaskSerializable.from(fileReader.nextLine());
            allTasks.add(serializable.deserialize());
        }
        return allTasks;
    }

    private void writeAllTasks(List<Task> tasks) throws DukeException {
        File database = this.getDatabase();
        try {
            FileWriter fileWriter = new FileWriter(database);
            for (Task task : tasks) {
                fileWriter.write(task.serialize().toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(TaskDatabase.ERROR_ADDING_TASK_TO_DATABASE);
        }
    }

    private void appendTask(Task task) throws DukeException {
        File database = this.getDatabase();
        try {
            // Set the 'append' flag to true to avoid overwriting existing file entries
            FileWriter fileWriter = new FileWriter(database, true);
            fileWriter.write(task.serialize().toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(TaskDatabase.ERROR_ADDING_TASK_TO_DATABASE);
        }
    }
}
