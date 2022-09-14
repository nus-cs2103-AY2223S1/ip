package rattus.chatbot.storage;

import static rattus.chatbot.common.Message.MESSAGE_INVALID_FILE_NAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.task.TaskList;

/**
 * A storage for any task data that requires storing between sessions.
 *
 * @author jq1836
 */
public class Storage {
    public static final String DATA_STORAGE_PATH_PREFIX = "data";

    /**
     * A file loader to load the list of tasks stored in the application.
     */
    private final TaskFileLoader fileLoader;

    /**
     * A file saver to save the list of tasks in a file.
     */
    private final TaskFileSaver fileSaver;

    private final File file;

    private Storage(File file) {
        this.file = file;
        fileLoader = new TaskFileLoader(file);
        fileSaver = new TaskFileSaver(file);
    }

    /**
     * Factory method to create an instance of Storage using the path from the project root. Creates a file if file
     * does not exist.
     *
     * @param path The file name for the data to be saved on.
     * @return An instance of storage corresponding to the path provided.
     */
    public static Storage from(String path) {
        try {
            File data = new File(DATA_STORAGE_PATH_PREFIX);
            if (!data.exists()) {
                data.mkdir();
            }

            String fullPath = String.format("%s/%s.txt", DATA_STORAGE_PATH_PREFIX, path);
            File file = new File(fullPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            return new Storage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Factory method to create an instance of Storage using a Java file. Does not create a file if it does not exist.
     *
     * @param file The file to use as a storage.
     * @return An instance of storage corresponding to the file provided.
     * @throws InvalidInputException If the file does not exist.
     */
    public static Storage of(File file) throws InvalidInputException {
        assert (new File(DATA_STORAGE_PATH_PREFIX).exists());
        if (!file.exists()) {
            throw new InvalidInputException(MESSAGE_INVALID_FILE_NAME);
        }
        return new Storage(file);
    }

    /**
     * Returns true if the storage file has the same path as the argument file and false otherwise.
     *
     * @param file The file to check for same path.
     * @return True if the storage file has the same path as the argument file and false otherwise.
     */
    public boolean hasSameFile(File file) {
        return this.file.equals(file);
    }

    /**
     * Returns a list of tasks after loading them from the stored file.
     *
     * @return A list of tasks loaded from the stored file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion of the encoded task is not in the correct format.
     */
    public TaskList getTasks() throws FileNotFoundException, InvalidInputException {
        return fileLoader.getTasks();
    }

    /**
     * Sends the list of tasks to the fileSaver to be stored in a file.
     *
     * @param taskList The list of tasks to be stored.
     */
    public void save(TaskList taskList) {
        fileSaver.saveTaskList(taskList);
    }
}
