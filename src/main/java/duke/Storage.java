package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.task.TaskList;

/**
 * This class saves and retrieves Tasks to and fro the hard disk.
 */
public class Storage {
    private final Path filePath;
    private final Path folderPath;

    // @@author jorrdansoh-reused
    // Reused from https://github.com/teikjun/duke
    // with minor modifications
    Storage() {
        String userDir = System.getProperty("user.dir");
        this.filePath = Paths.get(userDir + "/data/duke.txt");
        this.folderPath = Paths.get(userDir + "/data");
    }
    // @@author

    /**
     * Loads the TaskList that is stored in the filePath.
     *
     * @return The TaskList that has been loaded.
     * @throws DukeException If there is a problem with reading the file.
     */
    public TaskList load() throws DukeException {
        try {
            createFile();
            return TaskList.decode(Files.readAllLines(this.filePath));
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the given TaskList in the filePath.
     *
     * @param taskList The TaskList to be saved.
     * @throws DukeException If there is a problem with writing to the file.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            Files.write(this.filePath, taskList.encode());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    // @@author jorrdansoh-reused
    // Reused from https://github.com/teikjun/duke
    // with minor modifications
    /**
     * Creates the directory and/or file if they have not been created yet.
     *
     * @throws IOException If there is a problem with creating the directory or file.
     */
    public void createFile() throws IOException {
        if (Files.notExists(this.folderPath)) {
            Files.createDirectory(this.folderPath);
        }
        if (Files.notExists(this.filePath)) {
            Files.createFile(this.filePath);
        }
    }
    // @@author
}
