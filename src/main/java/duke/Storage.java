package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage class that handles the storage and retrieval of tasks.
 */
public class Storage {
    private final String directoryPath;
    private String fileName;

    /**
     * Constructs a new Storage in the application folder given a filename.
     *
     * @param fileName The name of the file to store tasks in.
     * @throws DukeException Exception thrown if the file cannot be found.
     */
    public Storage(String fileName) throws DukeException {
        String directoryPath = null;
        try {
            directoryPath = Path.of(this.getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation().toURI()).toString();
            directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf(File.separator));
            directoryPath = URLDecoder.decode(directoryPath, StandardCharsets.UTF_8)
                    .concat(File.separator + "data");
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    directoryPath = null;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (directoryPath == null) {
            throw new DukeException("Unable to save tasks to disk.");
        }
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        return loadTasks(this.fileName);
    }

    /**
     * Loads the list of task from a previously save text file.
     *
     * @param fileName Name of the file.
     * @return The list of tasks.
     * @throws DukeException Exception thrown if the file couldn't be read properly or contains invalid input.
     */
    public ArrayList<Task> loadTasks(String fileName) throws DukeException {
        this.fileName = fileName;
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(directoryPath.concat(File.separator + fileName));
        if (!file.exists()) {
            return tasks;
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            br.lines().forEach(line -> {
                String[] entries = line.strip().split("\\|", 3);
                Task.Type type = Task.Type.decode(entries[0]);
                boolean isCompleted = entries[1].equals("1");
                switch (type) {
                case DEADLINE:
                    try {
                        tasks.add(Deadline.decode(entries[2], isCompleted));
                    } catch (DukeException e) {
                        break;
                    }
                    break;
                case EVENT:
                    tasks.add(Event.decode(entries[2], isCompleted));
                    break;
                case TODO:
                    tasks.add(ToDo.decode(entries[2], isCompleted));
                    break;
                default:
                    break;
                }
            });
            br.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Could not read file! " + e.getMessage());
        }

    }

    private void writeToFile(String fileName, String content) throws DukeException {
        String filePath = directoryPath.concat(File.separator + fileName);
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            throw new DukeException("Could not save file! " + e.getMessage());
        }
    }

    /**
     * Encodes and saves the given list of tasks to a file.
     *
     * @param tasks the list of tasks to save.
     * @throws DukeException Exception thrown if the file cannot be found or modified.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.encode()).append(System.lineSeparator());
        }
        this.writeToFile(fileName, sb.toString());
    }
}
