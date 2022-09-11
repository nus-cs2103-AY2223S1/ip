package duke.storage;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.common.exceptions.StorageException;
import duke.data.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Loads and saves the list of tasks in a file.
 */
public class Storage {
    private static final String FILE_PATH = "data/tasklist.txt";
    private static final String OLD_FILE_PATH = "data/oldtasklist.txt";
    private static final String TEMP_FILE_PATH = "data/temptasklist.txt";
    private static final String INVALID_STORAGE_DATA = "Invalid storage data";

    private void createFile(File file) throws StorageException {
        new File("data").mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException();
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
     * Saves the list of tasks to a file.
     * @param taskList The list of tasks to be saved.
     * @throws StorageException If there is an IOException saving the list of tasks to the file.
     */
    public void save(TaskList taskList) throws StorageException {
        int numTasks = taskList.numTasks();
        FileWriter writer = null;

        try {
            Files.copy(Paths.get(FILE_PATH), Paths.get(OLD_FILE_PATH), REPLACE_EXISTING);
            writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < numTasks; i++) {
                Task task = taskList.getTask(i);
                writer.write(formatTask(task));
            }
        } catch (IOException e) {
            throw new StorageException();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new StorageException();
                }
            }
        }
    }

    /**
     * Reads and returns the list of tasks from the file.
     * @return The list of tasks from the file.
     * @throws StorageException If there is an IOException reading the list of tasks from the file.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        createFile(file);
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitInputArray = line.split(";", 4);
                assert splitInputArray.length == 4 : INVALID_STORAGE_DATA;
                String type = splitInputArray[0];
                String status = splitInputArray[1];
                String description = splitInputArray[2];
                String date = splitInputArray[3];

                Task task;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, date);
                } else {
                    task = new Event(description, date);
                }
                tasks.add(task);
                task.changeStatus(status.equals("X"));
            }
            return tasks;
        } catch (IOException e) {
            throw new StorageException();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Undos the most recent change to the task list.
     * @param taskList The list of tasks to change.
     * @throws StorageException If there is an IOException saving the modified list of tasks.
     */
    public void undo(TaskList taskList) throws StorageException {
        try {
            Files.copy(Paths.get(FILE_PATH), Paths.get(TEMP_FILE_PATH), REPLACE_EXISTING);
            Files.copy(Paths.get(OLD_FILE_PATH), Paths.get(FILE_PATH), REPLACE_EXISTING);
            Files.copy(Paths.get(TEMP_FILE_PATH), Paths.get(OLD_FILE_PATH), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException();
        }
        taskList.changeTasks(load());
    }
}
