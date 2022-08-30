package duke;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import duke.task.Task;

/**
 * Storage class containing all the logic for loading and saving to file.
 */
public class Storage {
    private String fileName;
    private String folderPath;

    /**
     * Creates new Storage object.
     *
     * @param fileName Path to save file.
     * @param folderPath Path to folder containing save file.
     */
    public Storage(String fileName, String folderPath) {
        this.fileName = fileName;
        this.folderPath = folderPath;
    }

    /**
     * Loads text from save file.
     *
     * @return A list of strings containing lines in save file.
     * @throws DukeException if save file is not found or cannot be parsed.
     */
    public List<String> load() throws DukeException {
        try {
            List<String> res = Files.readAllLines(Paths.get(fileName));
            return res;
        } catch (Exception e) {
            throw(new DukeException("No saved data found!"));
        }
    }

    /**
     * Saves text containing task data into save file.
     *
     * @param tasks A list of strings containing lines to save.
     * @throws Exception if save file cannot be written.
     */
    public void save(TaskList tasks) throws DukeException {
        String res = "";
        for (Task task : tasks.tasks()) {
            res += task;
            res += "\n";
        }

        try {
            Files.createDirectories(Paths.get(folderPath));
            Files.write(Paths.get(fileName), res.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw(new DukeException("Error writing to file!"));
        }

    }
}
