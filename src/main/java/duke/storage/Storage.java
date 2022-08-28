package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Hard disk storage for the Duke program.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructor for the Storage class.
     * @param filePath Path to the file in the hard disk.
     */
    public Storage(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("Error creating storage path");
        }

        this.filePath = filePath;
    }

    /**
     * Read tasks from storage.
     * @return A List of Strings, each representing a task.
     */
    public List<String> read() {
        List<String> lines;
        try {
            lines = Files.readAllLines(this.filePath);
        } catch (IOException e) {
            throw new DukeException("Unable to read " + this.filePath);
        }
        return lines;
    }

    /**
     * Write the list of tasks to the hard disk.
     * @param tasks Tasks to write.
     */
    public void write(TaskList tasks) {
        List<String> taskStrings = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            taskStrings.add(tasks.getTask(i).toStorage());
        }
        try {
            Files.write(this.filePath, taskStrings);
        } catch (IOException e) {
            throw new DukeException("Unable to write to " + this.filePath);
        }
    }
}
