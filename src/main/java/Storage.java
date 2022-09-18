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

/**
 * Storage class to store KKBot's Task List onto the hard drive.
 *
 * @author AkkFiros
 */

public class Storage {
    private static final String FILE_PATH = "data/task-list.txt";
    private static final String PAST_FILE_PATH = "data/past-task-list.txt";
    private static final String TEMP_FILE_PATH = "data/temp-task-list.txt";
    private static final String INVALID_STORAGE = "Invalid Storage data";

    /**
     * Method to load tasks stored in the hard drive
     * @return The list of previously stored tasks
     * @throws StorageException when there is an error loading a file
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        File tasksFile = new File(FILE_PATH);
        createFile(tasksFile);

        try (BufferedReader r = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = r.readLine();
            while (line != null) {
                String[] splitLine = line.split(";", 4);
                assert splitLine.length == 4 : INVALID_STORAGE;
                String type = splitLine[0];
                String status = splitLine[1];
                String description = splitLine[2];
                String date = splitLine[3];

                Task task = createTask(type, description, date);
                task.changeStatus(status.equals("X"));
                tasks.add(task);

                line = r.readLine();
            }

            return tasks;
        } catch (IOException e) {
            throw new StorageException("Oh no... Your file can't be loaded...");
        }
    }

    /**
     * Method to create a file in the hard drive
     * @param file the file to store in the hard drive
     * @throws StorageException when there is an error creating the file
     */
    private void createFile(File file) throws StorageException {
        new File("data").mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Oh no... Your file can't be created...");
        }
    }

    /**
     * Method to create a task after reading stored file.
     * @param type the type of task
     * @param description the description of task
     * @param date the date of task
     * @return returns the task stored in the hard drive
     * @throws StorageException when there is an error creating the task
     */
    private Task createTask(String type, String description, String date) throws StorageException {
        switch (type) {
            case "T":
                return new ToDo(description);
            case "D":
                return new Deadline(description, date);
            case "E":
                return new Event(description, date);
            default:
                throw new StorageException("Oh no... Your file can't be read...");
        }
    }

    /**
     * Method to save a file to the hard drive
     * @param tasks the list of tasks to store to hard drive
     * @throws StorageException when there is an error storing the list to the hard drive
     */
    public void save(TaskList tasks) throws KKBotException {
        int numOfTasks = tasks.getNumberOfTasks();
        copyFile(Paths.get(FILE_PATH), Paths.get(PAST_FILE_PATH));
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < numOfTasks; i++) {
                Task task = tasks.getTask(i);
                w.write(formatTask(task));
            }
        } catch (IOException e) {
            throw new StorageException("Oh no... Your file can't be saved...");
        }
    }

    /**
     * Method to copy a file in the hard drive to another file
     * @param source the source file to copy from
     * @param target the target file to copy to
     * @throws StorageException when there is an error copying the stored file
     */
    public void copyFile(Path source, Path target) throws StorageException {
        try {
            Files.copy(source, target, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Oh no... Your data can't be backed up...");
        }
    }

    /**
     * Method to separate a task into its component for formatting
     * @param task the task to format
     * @return the components of the task as a string
     */
    private String formatTask(Task task) {
        String type = task.getType();
        String status = task.getStatusIcon();
        String description = task.getDescription();
        String date = task.getDate();
        return String.format("%s, %s, %s, %s", type, status, description, date)
                + System.lineSeparator();
    }
}
