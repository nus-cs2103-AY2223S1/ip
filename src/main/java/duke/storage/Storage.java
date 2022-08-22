package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Storage {
    private final Path filePath;

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

    public List<String> load() {
        List<String> lines;
        try {
            lines = Files.readAllLines(this.filePath);
        } catch (IOException e) {
            throw new DukeException("Unable to read " + this.filePath);
        }
        return lines;
    }

    public void write(TaskList tasks) {
        try {
            Files.write(this.filePath, tasks.toStorage());
        } catch (IOException e) {
            throw new DukeException("Unable to write to " + this.filePath);
        }
    }
}
