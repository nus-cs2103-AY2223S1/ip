package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class of the Duke bot
 */
public class Duke{

    /**
     * Storage object that handles loading tasks from the
     * file and saving tasks in the file
     */
    private Storage storage;

    /**
     * TaskLists object that contains a task list containing Task objects
     */
    private TaskList tasks;

    /**
     * A constructor that initializes the Duke object
     * @param filePath the filepath of the Duke text file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        return Parser.parse(input, tasks, storage);
    }
}