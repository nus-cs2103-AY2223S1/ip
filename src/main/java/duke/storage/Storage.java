package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.FileDoesNotExistException;
import duke.exception.FileIoException;
import duke.task.TaskList;

/**
 * A Storage class that encapsulates the action of saving and loading task list in Duke from a specific storage file.
 */
public class Storage {

    private static final String DEFAULT_STORAGE_FILE = "task.txt";
    private static final String CURRENT_DIRECTORY = "user.dir";
    private static final String currentDir = System.getProperty(CURRENT_DIRECTORY);
    private final Path path;

    /**
     * Constructs a Storage Object with specifying the current directory as the directory to put the storage file.
     */
    public Storage() {
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_FILE);
    }


    /**
     * Saves the tasks in the current task list of Duke into the storage file
     *
     * @param taskList the current task list in Duke
     * @throws FileDoesNotExistException Throws an exception when the storage file does not exist
     */
    public void save(TaskList taskList) throws FileIoException {
        List<String> encodedTasks = StorageEncoder.encode(taskList);

        try {
            PrintWriter printWriter = new PrintWriter(path.toFile());
            for (String encodedTask : encodedTasks) {
                printWriter.println(encodedTask);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            create();
        }

    }

    /**
     * Loads the task list from the storage file when Duke initialised.
     *
     * @return the TaskList object
     * @throws DukeException throws an exception when encountering unexpected behaviours
     */
    public TaskList load() throws DukeException {
        if (!Files.exists(path)) {
            return new TaskList();
        }
        return StorageDecoder.decode(path);
    }

    /**
     * Creates a file.
     * @throws FileIoException throws an exception when encountering error in creating the file
     */
    public void create() throws FileIoException {
        File newFile = path.toFile();
        try {
            boolean hasCreated = newFile.createNewFile();
            simpleFileCreationCheck(hasCreated);
        } catch (IOException e) {
            throw new FileIoException("Error creating file: " + path);
        }
    }

    private void simpleFileCreationCheck(boolean hasCreated) throws FileIoException {
        if (!hasCreated) {
            throw new FileIoException("Unsuccessful in creating storage file !");
        }
    }

}
