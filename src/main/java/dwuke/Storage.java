package dwuke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dwuke.task.TaskList;

/**
 * This class saves tasks to hard disk
 */
public class Storage {
    private Path filePath;
    private Path folderPath;

    Storage() {
        String userDir = System.getProperty("user.dir");
        this.filePath = Paths.get(userDir + "/data/dwuke.txt");
        this.folderPath = Paths.get(userDir + "/data");
    }

    /**
     * Loads the TaskList that is stored in the filePath.
     *
     * @return The TaskList that has been loaded.
     * @throws DwukeException If there is a problem with reading the file.
     */
    public TaskList load() throws DwukeException {
        try {
            createFile();
            return TaskList.decode(Files.readAllLines(this.filePath));
        } catch (IOException e) {
            throw new DwukeException(e.getMessage());
        }
    }

    /**
     * Saves the given TaskList in the filePath.
     *
     * @param taskList The TaskList to be saved.
     * @throws DwukeException If there is a problem with writing to the file.
     */
    public void save(TaskList taskList) throws DwukeException {
        try {
            Files.write(this.filePath, taskList.encode());
        } catch (IOException e) {
            throw new DwukeException(e.getMessage());
        }
    }

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
}
