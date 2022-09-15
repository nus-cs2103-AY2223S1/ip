package doemon.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import doemon.exception.TaskDataException;
import doemon.task.Deadline;
import doemon.task.Event;
import doemon.task.Task;
import doemon.task.Todo;

/**
 * Handles storing and loading of tasks into a specified data file.
 */
public class Storage {
    /** Path of the data file to write to. */
    private String filePath;
    /** Lines of save strings to be saved in data file */
    private ArrayList<String> taskSaveStrings;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path of the date file to write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskSaveStrings = new ArrayList<>();
    }

    /**
     * Adds task data to save string list and updates the data file.
     *
     * @param task Task to be added.
     * @throws TaskDataException If data file update fails.
     */
    public void addTaskData(Task task) throws TaskDataException {
        this.taskSaveStrings.add(task.getSaveString());
        saveTasks();
    }

    /**
     * Marks task stored in save string list and updates the date file.
     *
     * @param taskIndex Index of task to be marked.
     * @throws TaskDataException If data file update fails.
     */
    public void markTaskData(int taskIndex) throws TaskDataException {
        this.taskSaveStrings.set(
                taskIndex,
                this.taskSaveStrings.get(taskIndex).replaceFirst("0", "1"));
        saveTasks();
    }

    /**
     * Unmarks task stored in save string list and updates the data file.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws TaskDataException If data file update fails.
     */
    public void unmarkTaskData(int taskIndex) throws TaskDataException {
        this.taskSaveStrings.set(
                taskIndex,
                this.taskSaveStrings.get(taskIndex).replaceFirst("1", "0"));
        saveTasks();
    }

    /**
     * Deletes task stored in save string list and updates the data file.
     *
     * @param taskIndex Index of task to be deleted.
     * @throws TaskDataException If data file update fails.
     */
    public void deleteTaskData(int taskIndex) throws TaskDataException {
        this.taskSaveStrings.remove(taskIndex);
        saveTasks();
    }

    /**
     * Loads previously stored tasks from data file and returns tasks as an ArrayList.
     *
     * @return List of tasks stored in data file.
     * @throws TaskDataException If data file read fails.
     */
    public ArrayList<Task> loadTasks() throws TaskDataException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        // Check and create data directory if necessary.
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Create file if it does not exist.
        File taskDataFile = new File(this.filePath);
        if (!taskDataFile.exists()) {
            try {
                taskDataFile.createNewFile();
            } catch (IOException e) {
                throw new TaskDataException();
            }
            return loadedTasks;
        }
        // Load tasks.
        try (BufferedReader reader = new BufferedReader(new FileReader(taskDataFile))) {
            String taskData = reader.readLine();
            while (taskData != null) {
                String[] taskDataArr = taskData.split(" \\| ");
                String flag = taskDataArr.length > 3 ? taskDataArr[3] : null;
                Task task = getTaskFromSaveString(taskDataArr[0], taskDataArr[1], taskDataArr[2], flag);
                loadedTasks.add(task);
                this.taskSaveStrings.add(taskData);
                taskData = reader.readLine();
            }
        } catch (IOException e) {
            throw new TaskDataException();
        }
        return loadedTasks;
    }

    /**
     * Saves and updates latest list of task strings.
     *
     * @throws TaskDataException If data file update fails.
     */
    private void saveTasks() throws TaskDataException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            StringBuilder toWrite = new StringBuilder();
            int i = 0;
            while (i < this.taskSaveStrings.size() - 1) {
                toWrite.append(this.taskSaveStrings.get(i++));
                toWrite.append('\n');
            }
            if (this.taskSaveStrings.size() > 0) {
                toWrite.append(this.taskSaveStrings.get(i));
            }
            writer.write(toWrite.toString());
        } catch (IOException e) {
            throw new TaskDataException();
        }
    }

    private Task getTaskFromSaveString(String type, String mark, String description, String flag)
            throws TaskDataException {
        boolean isMarked = mark.equals("1");
        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, flag);
            break;
        case "E":
            task = new Event(description, flag);
            break;
        default:
            throw new TaskDataException();
        }
        if (isMarked) {
            task.mark();
        }
        return task;
    }
}
