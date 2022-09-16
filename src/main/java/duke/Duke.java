package duke;

import java.io.FileNotFoundException;

/**
 * The class of the Duke bot
 */
public class Duke{

    /**
     * Storage object that handles loading tasks from the
     * file and saving tasks in the file
     */
    private final Storage storage;

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
            tasks = new TaskList();
        }
    }

    /**
     * A method that takes in the users input and return Dukes output
     * @param input Users input
     * @return String Duke's output
     */
    public String getResponse(String input) {
        return Parser.parse(input, tasks, storage);
    }
}