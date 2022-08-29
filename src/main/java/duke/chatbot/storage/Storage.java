package duke.chatbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

/**
 * A storage for any task data that requires storing between
 * sessions.
 */
public class Storage {
    /** A file loader to load the list of tasks stored in the application */
    private final TaskFileLoader fileLoader;

    /** A file saver to save the list of tasks in a file */
    private final TaskFileSaver fileSaver;

    private Storage(File file) {
        fileLoader = new TaskFileLoader(file);
        fileSaver = new TaskFileSaver(file);
    }

    /**
     * Factory method to create an instance of Storage.
     * @param path The file name for the data to be saved on.
     * @return An instance of storage corresponding to the path
     *     provided.
     */
    public static Storage of(String path) {
        try {
            File data = new File("data");
            if (!data.exists()) {
                data.mkdir();
            }

            File file = new File("data/" + path);
            if (!file.exists()) {
                file.createNewFile();
            }
            return new Storage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return list of tasks after loading them from the stored file.
     * @return A list of tasks loaded from the stored file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion
     *     of the encoded task is not in the correct format.
     */
    public TaskList getTaskList() throws FileNotFoundException, InvalidInputException {
        return fileLoader.getTaskList();
    }

    /**
     * Sends the list of tasks to the fileSaver to be stored
     * in a file.
     * @param taskList The list of tasks to be stored.
     */
    public void save(TaskList taskList) {
        fileSaver.saveTaskList(taskList);
    }
}
