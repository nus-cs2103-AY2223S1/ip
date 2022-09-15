package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.data.TaskList;
import duke.data.exception.DukeException;

/**
 * This class saves and loads tasks into and from the text file created
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new storage
     * @param filePath The relative path that describes where to store the items
     */
    public Storage(String filePath) {
        assert isPathValid(filePath) : "Invalid file path";
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads data from the storage
     * @return A list of tasks
     */
    public ArrayList<String> load() {
        File file = new File(filePath);
        ArrayList<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                tasks.add(currentLine);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file as described by the filePath
     * @param tasks The list of tasks
     * @throws DukeException If there are invalid inputs
     */
    public void save(TaskList tasks) throws DukeException {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file)) {
            int len = tasks.getSize();
            for (int i = 0; i < len; i++) {
                fileWriter.write((tasks.getTask(i).stringify()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isPathValid(String filePath) {
        try {
            Paths.get(filePath);
            return true;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }
}
