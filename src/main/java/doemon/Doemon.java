package doemon;

import doemon.command.Command;
import doemon.exception.DoemonException;
import doemon.exception.TaskDataException;
import doemon.parser.Parser;
import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.TaskList;

/**
 * Doemon chat bot and task manager.
 */
public class Doemon {
    /** The file path of the file where tasks will be saved to. */
    private static final String TASK_FILE_PATH = "./data/duke.txt";

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
        this.response = new Response();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (TaskDataException tde) {
            this.tasks = new TaskList();
        }
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
        return c.execute(this.tasks, this.response, this.storage);
    }
}
