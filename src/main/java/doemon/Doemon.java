package doemon;

import doemon.command.Command;
import doemon.exception.DoemonException;
import doemon.exception.TaskDataException;
import doemon.parser.Parser;
import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.TaskList;

/**
 * Doemon chatbot and task manager.
 */
public class Doemon {
    /** The storage instance used to load and save tasks to data file. */
    private Storage storage;
    /** List of tasks. */
    private TaskList tasks;
    /** Response helper class that returns response strings. */
    private Response response;

    /**
     * Constructor for Doemon.
     *
     * @param filePath Path of the file where tasks will be saved to.
     */
    public Doemon(String filePath) {
        assert filePath != null : "File path should not be null";
        response = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (TaskDataException tde) {
            tasks = new TaskList();
        }
        assert tasks != null : "The tasks field should not be empty";
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input.
     * @return Response string.
     * @throws DoemonException If any exception is thrown during parsing.
     */
    public String getResponse(String input) throws DoemonException {
        Command c = Parser.parse(input);
        return c.execute(tasks, response, storage);
    }
}
