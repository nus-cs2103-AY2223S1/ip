package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the file storage for Duke.
 */
public class Storage {

    private Path dir;
    private Path file;

    public Storage(String dirName, String fileName) {
        this.dir = Paths.get(dirName);
        this.file = Paths.get(dirName, fileName);
    }

    /**
     * Initializes the directory for the save file if it does not exist yet.
     */
    public void initializeDir() throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
    }

    /**
     * Initializes the save file if it does not exist yet.
     */
    public void initializeFile() throws IOException {
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
    }

    /**
     * Reads a task from the data file and adds it to the working TaskList.
     *
     * @param data saved task in the form of a string in the data file
     * @param tasks the TaskList the task is being added to
     */
    private static void addTaskToArrayList(String data, ArrayList<Task> tasks) {
        String[] entries = data.split("\\|");
        switch (entries[0]) {
        case "T":
            if (entries[1].equals("1")) {
                tasks.add(new Todo(entries[2], true));
            } else {
                tasks.add(new Todo(entries[2], false));
            }
            break;

        case "D":
            if (entries[1].equals("1")) {
                tasks.add(new Deadline(entries[2], entries[3], true));
            } else {
                tasks.add(new Deadline(entries[2], entries[3], false));
            }
            break;

        case "E":
            if (entries[1].equals("1")) {
                tasks.add(new Event(entries[2], entries[3], true));
            } else {
                tasks.add(new Event(entries[2], entries[3], false));
            }
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Reads the data file and adds all saved tasks to the working TaskList.
     */
    public ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> result = new ArrayList<>();
        List<String> previousTasks = Files.readAllLines(file);
        try {
            previousTasks.forEach(t -> addTaskToArrayList(t, result));
        } catch (IndexOutOfBoundsException e) {
            throw new IOException();
        }
        return result;
    }

    /**
     * Updates the data file with all tasks currently in the working TaskList.
     */
    public void updateFile(TaskList ts) throws IOException {
        ArrayList<String> taskDescriptions = new ArrayList<>();
        ts.getAllTasks().forEach(t -> taskDescriptions.add(t.toSaveData()));
        Files.write(file, taskDescriptions);
    }
}
