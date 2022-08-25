package duke.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.FileDoesNotExistException;
import duke.exception.StorageOperationException;
import duke.task.TaskList;

/**
 * A Storage class that encapsulates the action of saving and loading task list in Duke from a specific storage file.
 */
public class Storage {

    private static final String DEFAULT_STORAGE_FILE = "tasklist.txt";
    private static final String CURRENT_DIRECTORY = "user.dir";

    private Path path;

    /**
     * Constructs a Storage Object with specifying the current directory as the directory to put the storage file.
     * @throws FileDoesNotExistException
     */
    public Storage() throws FileDoesNotExistException {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
<<<<<<< HEAD
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_FILE);
    }

    public void save(TaskList taskList) throws StorageOperationException {
=======
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_SOURCE_FOLDER, DEFAULT_STORAGE_FILE);
    }

    /**
     * Saves the tasks in the current task list of Duke into the storage file
     * @param taskList the current task list in Duke
     * @throws FileDoesNotExistException Throws an exception when the storage file does not exist.
     */
    public void save(TaskList taskList) throws FileDoesNotExistException {
>>>>>>> branch-A-JavaDoc
        List<String> encodedTasks = StorageEncoder.encode(taskList);

        try {
            PrintWriter printWriter = new PrintWriter(path.toFile());
            for (int i =0 ;i < encodedTasks.size(); i++) {
                printWriter.println(encodedTasks.get(i));
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            create();
        }

    }

    /**
     * Loads the task list from the storage file when Duke initialised.
     * @return the TaskList object
     * @throws DukeException throws an exception when encountering unexpected behaviours.
     */
    public TaskList load() throws DukeException {
        if (!Files.exists(path)) {
            return new TaskList();
        }
        return StorageDecoder.decode(path);
    }

    public void create() throws StorageOperationException {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        Path newPath = Paths.get(currentDir,DEFAULT_STORAGE_FILE);
        File newFile = newPath.toFile();
        try {
            newFile.createNewFile();
            this.path = newPath;
        } catch (IOException e) {
            throw new StorageOperationException("Error creating file: " + path);
        }
    }

}
