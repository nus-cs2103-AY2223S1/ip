package duke.storage;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.data.TaskList;
import duke.storage.exceptions.StorageException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Creates, loads and saves the list of tasks in a file.
 */
public class Storage {
    private static final String FILE_PATH = "data/task-list.txt";
    private static final String PREV_FILE_PATH = "data/prev-task-list.txt";
    private static final String TEMP_FILE_PATH = "data/temp-task-list.txt";

    /**
     * Creates a data file if it does not exist. Reads and returns the list of tasks from the file.
     * @return The list of tasks from the file.
     * @throws StorageException If there is an IOException reading the list of tasks from the file.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        File taskListFile = new File(FILE_PATH);
        createFile(taskListFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            while (line != null) {
                String[] splitLineArray = line.split(";", 4);
                assert splitLineArray.length == 4 : "Invalid storage data";
                String type = splitLineArray[0];
                String status = splitLineArray[1];
                String description = splitLineArray[2];
                String date = splitLineArray[3];

                Task task = createTask(type, description, date);
                task.changeStatus(status.equals("X"));
                tasks.add(task);

                line = reader.readLine();
            }
            return tasks;
        } catch (IOException e) {
            throw new StorageException("I've encountered an error while loading your data file.");
        }
    }

    private void createFile(File file) throws StorageException {
        new File("data").mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("I've encountered an error while creating your data file.");
        }
    }

    private Task createTask(String type, String description, String date) throws StorageException {
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            return new Deadline(description, date);
        case "E":
            return new Event(description, date);
        default:
            throw new StorageException("I've encountered an error while reading your data file.");
        }
    }

    /**
     * Saves the list of tasks to a file.
     * @param taskList The list of tasks to be saved.
     * @throws StorageException If there is an IOException saving the list of tasks to the file.
     */
    public void save(TaskList taskList) throws StorageException {
        int numTasks = taskList.numTasks();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Files.copy(Paths.get(FILE_PATH), Paths.get(PREV_FILE_PATH), REPLACE_EXISTING);
            for (int i = 0; i < numTasks; i++) {
                Task task = taskList.getTask(i);
                writer.write(formatTask(task));
            }
        } catch (IOException e) {
            throw new StorageException("I've encountered an error while saving your data file.");
        }
    }

    private String formatTask(Task task) {
        String type = task.getType();
        String status = task.getStatusIcon();
        String description = task.getDescription();
        String date = task.getDate();
        return String.format("%s;%s;%s;%s", type, status, description, date) + System.lineSeparator();
    }

    /**
     * Undos the most recent change to the task list.
     * @param taskList The list of tasks to change.
     * @throws StorageException If there is an IOException saving the modified list of tasks.
     */
    public void undo(TaskList taskList) throws StorageException {
        Path prevPath = Paths.get(PREV_FILE_PATH);
        if (!Files.exists(prevPath)) {
            throw new StorageException("You have not made changes to your task list yet!");
        }

        Path path = Paths.get(FILE_PATH);
        Path tempPath = Paths.get(TEMP_FILE_PATH);
        try {
            Files.copy(path, tempPath, REPLACE_EXISTING);
            Files.copy(prevPath, path, REPLACE_EXISTING);
            Files.copy(tempPath, prevPath, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("I've encountered an error while retrieving your previous data file.");
        }
        taskList.changeTasks(load());
    }
}
