package cheese;

import cheese.command.Command;
import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.parser.Parser;
import cheese.storage.Storage;

/**
 * Represents an application used to manage tasks.
 */
public class Cheese {
    /** List of tasks. */
    private TaskList taskList;

    /** Storage to interact with save file. */
    private Storage storage;

    /**
     * Constructs an instance of <code>Cheese</code>.
     *
     * @param filePath File path of save file.
     */
    public Cheese(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (CheeseException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns response from Cheese given user input.
     *
     * @param input Input from user.
     * @return Response from Cheese.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, storage);
        } catch (CheeseException e) {
            return e.getMessage();
        }
    }
}
