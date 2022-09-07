package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a <code>Duke</code> programme.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a Duke program.
     *
     * @param filePath directory to store task data
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        assert storage != null : "Unable to find data file";
        tasks = new TaskList(storage.load());
        assert tasks != null : "Unable to load data file";
    }

    /**
     * Returns Duke's response based on user's input.
     *
     * @param userInput input from user
     * @return duke's response
     */
    public String getResponse(String userInput) {
        String dukeResponse;
        try {
            Command c = Parser.parse(userInput);
            assert c != null : "Unable to parse command";
            dukeResponse = c.execute(tasks);
            storage.saveData(tasks);
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
